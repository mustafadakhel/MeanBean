package com.martin.meanbean.framework.sources.remote.retrofit

import com.martin.meanbean.framework.sources.remote.dto.BeansResponseDto
import com.martin.meanbean.framework.sources.remote.dto.DrinksResponseDto
import retrofit2.http.GET

interface MeanBeanApi {
	@GET("beans")
	suspend fun getBeans(): BeansResponseDto

	@GET("drinks")
	suspend fun getDrinks(): DrinksResponseDto
}