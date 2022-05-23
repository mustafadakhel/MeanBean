package com.martin.meanbean.data.sources.implementation.remote

import com.martin.meanbean.data.sources.contract.DrinksSource
import com.martin.meanbean.domain.models.Drink
import com.martin.meanbean.data.sources.implementation.remote.retrofit.MeanBeanApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDrinksSource @Inject constructor(private val meanBeanApi: MeanBeanApi) : DrinksSource {
	override suspend fun getDrinks() = meanBeanApi.getDrinks().drinks.map {
		Drink(
			id = it.id,
			name = it.name,
			image = it.image,
			description = it.desc
		)
	}

	override suspend fun getDrink(drinkId: Int) = meanBeanApi.getDrinks().drinks.find {
		it.id == drinkId
	}?.let {
		Drink(
			id = it.id,
			name = it.name,
			image = it.image,
			description = it.desc
		)
	}
}