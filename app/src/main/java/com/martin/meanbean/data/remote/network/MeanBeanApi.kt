package com.martin.meanbean.data.remote.network

import com.martin.meanbean.data.remote.dto.BeansResponseDto
import com.martin.meanbean.data.remote.dto.DrinksResponseDto
import retrofit2.http.GET

interface MeanBeanApi {
	@GET("beans")
	suspend fun getBeanTypes(): BeansResponseDto

	@GET("drinks")
	suspend fun getDrinkTypes(): DrinksResponseDto
}