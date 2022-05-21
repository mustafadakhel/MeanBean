package com.martin.meanbean.utils

import com.martin.meanbean.domain.entities.Wrap
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException


inline fun <T> wrapFlow(
	crossinline network: (suspend () -> T),
	noinline cached: (suspend () -> T?)? = null,
	noinline updateCache: (suspend (T) -> Unit)? = null
) = flow<Wrap<T>> {
	emitLoading()
	val cachedData = cached?.invoke()
	emitCache(cachedData)
	runCatching {
		network()
	}.onFailure {
		emitError(it, cachedData)
	}.onSuccess {
		emitSuccess(it)
		updateCache?.invoke(it)
	}
}

suspend fun <T> FlowCollector<Wrap<T>>.emitLoading() =
	emit(Wrap.loading())

suspend fun <T> FlowCollector<Wrap<T>>.emitCache(cache: T?) =
	emit(Wrap.cached(cache))

suspend fun <T> FlowCollector<Wrap<T>>.emitSuccess(data: T) =
	emit(Wrap.success(data))

suspend fun <T> FlowCollector<Wrap<T>>.emitError(
	exception: Throwable,
	cached: T? = null
) {
	if (exception is HttpException)
		emitNetworkError(exception, cached)
	else emitIOError(exception, cached)
}

suspend fun <T> FlowCollector<Wrap<T>>.emitNetworkError(
	exception: HttpException,
	cached: T? = null
) = emit(Wrap.networkError(exception, cached))

suspend fun <T> FlowCollector<Wrap<T>>.emitIOError(
	throwable: Throwable,
	cached: T? = null
) = emit(Wrap.ioError(throwable, cached))
