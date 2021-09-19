package com.martin.meanbean.domain.entities

import retrofit2.HttpException

/**
Result variations should not be mutually exclusive because:
1. Result can have data and error at the same time (in case network failed and data was fetched
from the db)
2. Result can be loading and have data at the same time (in case of a refresh or in case we
displayed data from db while updating it from the network)
 */
data class Result<T>(
	val isLoading: Boolean = true,
	val data: T? = null,
	val isError: Boolean = false,
	val errorType: ErrorType? = null
) {

	val hasData: Boolean get() = data != null

	sealed class ErrorType {
		data class NetworkError(val code: Int, val message: String) : ErrorType()
		data class IOError(val throwable: Throwable) : ErrorType()
	}

	companion object {
		fun <T> networkError(exception: HttpException): Result<T> {
			return Result(
				isLoading = false,
				data = null,
				isError = true,
				errorType = ErrorType.NetworkError(exception.code(), exception.message())
			)
		}

		fun <T> ioError(throwable: Throwable): Result<T> {
			return Result(
				isLoading = false,
				data = null,
				isError = true,
				errorType = ErrorType.IOError(throwable)
			)
		}

		fun <T> success(data: T): Result<T> {
			return Result(isLoading = false, data = data)
		}

		fun <T> loading(): Result<T> {
			return Result(isLoading = true, data = null)
		}
	}

	fun <T> changeData(
		isLoading: Boolean = this.isLoading,
		data: T? = null,
		isError: Boolean = this.isError,
		errorType: ErrorType? = this.errorType
	) = Result(isLoading, data, isError, errorType)
}
