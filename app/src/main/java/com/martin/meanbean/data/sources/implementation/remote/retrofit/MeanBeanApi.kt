package com.martin.meanbean.data.sources.implementation.remote.retrofit

import com.martin.meanbean.data.sources.implementation.remote.dto.BeansResponseDto
import com.martin.meanbean.data.sources.implementation.remote.dto.DrinksResponseDto
import retrofit2.http.GET

interface MeanBeanApi {
	@GET("beans")
	suspend fun getBeans(): BeansResponseDto

	@GET("drinks")
	suspend fun getDrinks(): DrinksResponseDto
}