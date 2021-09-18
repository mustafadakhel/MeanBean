package com.martin.meanbean.data.repository

import com.martin.meanbean.data.remote.network.MeanBeanApi
import com.martin.meanbean.domain.entities.BeanEntity
import com.martin.meanbean.domain.entities.DrinkEntity
import com.martin.meanbean.domain.entities.Result
import com.martin.meanbean.domain.mapper.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MeanBeanRepository @Inject constructor(
	private val meanBeanApi: MeanBeanApi,
	private val mapper: Mapper
) {
	suspend fun getBeanTypes(): Flow<Result<List<BeanEntity>>> = resultFlow {
		meanBeanApi.getBeanTypes().beans.map(mapper::mapBean)
	}

	suspend fun getDrinkTypes(): Flow<Result<List<DrinkEntity>>> = resultFlow {
		meanBeanApi.getDrinkTypes().drinks.map(mapper::mapDrink)
	}

	private fun <T> resultFlow(block: suspend () -> T): Flow<Result<T>> {
		//Todo: return data from db in case of network failure
		return flow<Result<T>> {
			emitLoading()
			emitSuccess(block())
		}.catchErrors()
	}

	private suspend fun <T> FlowCollector<Result<T>>.emitSuccess(data: T) {
		emit(Result.success(data))
	}

	private suspend fun <T> FlowCollector<Result<T>>.emitLoading() {
		emit(Result.loading())
	}

	private fun <T> Flow<Result<T>>.catchErrors(): Flow<Result<T>> {
		return catch {
			if (it is HttpException)
				emitNetworkError(it)
			else emitIOError(it)
		}
	}

	private suspend fun <T> FlowCollector<Result<T>>.emitNetworkError(exception: HttpException) {
		emit(Result.networkError(exception))
	}

	private suspend fun <T> FlowCollector<Result<T>>.emitIOError(throwable: Throwable) {
		emit(Result.ioError(throwable))
	}
}

