package com.martin.meanbean.utils

import com.martin.meanbean.domain.entities.Data
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException


inline fun <T> resultFlow(
	crossinline network: suspend () -> T,
	crossinline cached: suspend () -> T? = { null },
	crossinline updateCache: suspend (T) -> Unit = {}
) = flow<Data<T>> {
	emitLoading()
	val cachedData = cached()
	emitCache(cachedData)
	runCatching {
		network()
	}.onFailure {
		emitError(it, cachedData)
	}.onSuccess {
		emitSuccess(it)
		updateCache(it)
	}
}

suspend fun <T> FlowCollector<Data<T>>.emitLoading() =
	emit(Data.loading())

suspend fun <T> FlowCollector<Data<T>>.emitCache(cache: T?) =
	emit(Data.cached(cache))

suspend fun <T> FlowCollector<Data<T>>.emitSuccess(data: T) =
	emit(Data.success(data))

suspend fun <T> FlowCollector<Data<T>>.emitError(
	exception: Throwable,
	cached: T? = null
) {
	if (exception is HttpException)
		emitNetworkError(exception, cached)
	else emitIOError(exception, cached)
}

suspend fun <T> FlowCollector<Data<T>>.emitNetworkError(
	exception: HttpException,
	cached: T? = null
) = emit(Data.networkError(exception, cached))

suspend fun <T> FlowCollector<Data<T>>.emitIOError(
	throwable: Throwable,
	cached: T? = null
) = emit(Data.ioError(throwable, cached))
