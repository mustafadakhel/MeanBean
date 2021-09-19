package com.martin.meanbean.utils

import com.martin.meanbean.domain.entities.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException


inline fun <T> resultFlow(crossinline block: suspend () -> T) = flow<Result<T>> {
//Todo: return data from db in case of network failure
	emitLoading()
	emitSuccess(block())
}.catchErrors()

suspend fun <T> FlowCollector<Result<T>>.emitLoading() =
	emit(Result.loading())

suspend fun <T> FlowCollector<Result<T>>.emitSuccess(data: T) =
	emit(Result.success(data))

fun <T> Flow<Result<T>>.catchErrors() = catch {
	if (it is HttpException)
		emitNetworkError(it)
	else emitIOError(it)
}

suspend fun <T> FlowCollector<Result<T>>.emitNetworkError(exception: HttpException) =
	emit(Result.networkError(exception))

suspend fun <T> FlowCollector<Result<T>>.emitIOError(throwable: Throwable) =
	emit(Result.ioError(throwable))
