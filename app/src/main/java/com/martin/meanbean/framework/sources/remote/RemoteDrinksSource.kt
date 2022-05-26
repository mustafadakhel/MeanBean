package com.martin.meanbean.framework.sources.remote

import com.martin.meanbean.data.sources.DrinksSource
import com.martin.meanbean.framework.sources.remote.dto.toDrink
import com.martin.meanbean.framework.sources.remote.retrofit.MeanBeanApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDrinksSource @Inject constructor(private val meanBeanApi: MeanBeanApi) : DrinksSource {
	override suspend fun getDrinks() = meanBeanApi.getDrinks().drinks.map {
		it.toDrink()
	}

	override suspend fun getDrink(drinkId: Int) = meanBeanApi.getDrinks().drinks.find {
		it.id == drinkId
	}?.toDrink()
}