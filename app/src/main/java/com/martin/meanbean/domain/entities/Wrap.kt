package com.martin.meanbean.domain.entities

import retrofit2.HttpException

/**
Wrap variations should not be mutually exclusive because:
1. Wrap can have data and error at the same time (in case network failed and data was fetched
from the db)
2. Wrap can be loading and have data at the same time (in case of a refresh or in case we
displayed data from db while updating it from the network)
 */
@Suppress("MemberVisibilityCanBePrivate", "unused")
data class Wrap<T>(
	val isLoading: Boolean = true,
	val data: T? = null,
	val isError: Boolean = false,
	val errorType: ErrorType? = null
) {

	val states: List<ResultState>
		get() = mutableListOf<ResultState>().apply {
			if (isLoading) add(ResultState.Loading)
			if (isError) add(ResultState.Error(errorType))
			if (hasData) add(ResultState.Success(data))
		}

	val hasData: Boolean get() = data != null

	sealed class ResultState {
		data class Success<T>(val data: T) : ResultState() {
			override fun toString() = "Success with data: $data"
		}

		object Loading : ResultState() {
			override fun toString() = "Loading"
		}

		data class Error(val errorType: ErrorType?) : ResultState() {
			override fun toString() = "Error: $errorType"
		}
	}

	sealed class ErrorType {
		data class NetworkError(val code: Int, val message: String) : ErrorType() {
			override fun toString() = "Network Error: $code $message"
		}

		data class IOError(val throwable: Throwable) : ErrorType() {
			override fun toString() = throwable.toString()
		}
	}

	companion object {
		fun <T> networkError(exception: HttpException, altData: T? = null): Wrap<T> {
			return Wrap(
				isLoading = false,
				data = altData,
				isError = true,
				errorType = ErrorType.NetworkError(exception.code(), exception.message())
			)
		}

		fun <T> ioError(throwable: Throwable, altData: T? = null): Wrap<T> {
			return Wrap(
				isLoading = false,
				data = altData,
				isError = true,
				errorType = ErrorType.IOError(throwable)
			)
		}

		fun <T> success(data: T): Wrap<T> {
			return Wrap(isLoading = false, data = data)
		}

		fun <T> loading(): Wrap<T> {
			return Wrap()
		}

		fun <T> cached(cache: T?): Wrap<T> {
			return Wrap(isLoading = true, data = cache)
		}
	}
}
