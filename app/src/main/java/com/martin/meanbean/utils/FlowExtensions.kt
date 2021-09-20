package com.martin.meanbean.utils

import com.martin.meanbean.domain.entities.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException


inline fun <T> resultFlow(
	crossinline network: suspend () -> T,
	crossinline db: suspend () -> T? = { null }
) = flow<Data<T>> {
//Todo: return data from db in case of network failure
	emitLoading()
	emitSuccess(network())
}.catchErrors()

suspend fun <T> FlowCollector<Data<T>>.emitLoading() =
	emit(Data.loading())

suspend fun <T> FlowCollector<Data<T>>.emitSuccess(data: T) =
	emit(Data.success(data))

fun <T> Flow<Data<T>>.catchErrors() = catch {
	if (it is HttpException)
		emitNetworkError(it)
	else emitIOError(it)
}

suspend fun <T> FlowCollector<Data<T>>.emitNetworkError(exception: HttpException) =
	emit(Data.networkError(exception))

suspend fun <T> FlowCollector<Data<T>>.emitIOError(throwable: Throwable) =
	emit(Data.ioError(throwable))
