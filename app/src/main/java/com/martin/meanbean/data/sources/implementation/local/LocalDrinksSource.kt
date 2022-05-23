package com.martin.meanbean.data.sources.implementation.local

import com.martin.meanbean.data.sources.contract.MutableDrinksSource
import com.martin.meanbean.domain.models.Drink
import com.martin.meanbean.data.sources.implementation.local.db.DrinksDao
import com.martin.meanbean.data.sources.implementation.local.entities.DrinkEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDrinksSource @Inject constructor(private val drinksDao: DrinksDao) :
	MutableDrinksSource {
	override suspend fun getDrinks() = drinksDao.getDrinks().map {
		Drink(
			id = it.id,
			name = it.name,
			image = it.image,
			description = it.description
		)
	}

	override suspend fun getDrink(drinkId: Int) = drinksDao.getDrink(drinkId)?.let {
		Drink(
			id = it.id,
			name = it.name,
			image = it.image,
			description = it.description
		)
	}

	override suspend fun addDrink(drink: Drink) {
		drinksDao.insert(DrinkEntity(drink.id, drink.name, drink.image, drink.description))

	}

	override suspend fun addDrinks(drinks: List<Drink>) {
		drinksDao.insert(drinks.map { drink ->
			DrinkEntity(drink.id, drink.name, drink.image, drink.description)
		})
	}
}