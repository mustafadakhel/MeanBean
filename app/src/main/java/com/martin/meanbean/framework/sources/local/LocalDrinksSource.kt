package com.martin.meanbean.framework.sources.local

import com.martin.meanbean.data.sources.MutableDrinksSource
import com.martin.meanbean.framework.sources.local.db.DrinksDao
import com.martin.meanbean.framework.sources.local.entities.toDrink
import com.martin.meanbean.framework.sources.local.entities.toEntity
import com.martin.meanbean.domain.models.Drink
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDrinksSource @Inject constructor(private val drinksDao: DrinksDao) :
	MutableDrinksSource {
	override suspend fun getDrinks() = drinksDao.getDrinks().map { entity ->
		entity.toDrink()
	}

	override suspend fun getDrink(drinkId: Int) =
		drinksDao.getDrink(drinkId)?.toDrink() ?: throw CouldNotFindDrink

	override suspend fun addDrink(drink: Drink) {
		drinksDao.insert(drink.toEntity())
	}

	override suspend fun addDrinks(drinks: List<Drink>) {
		drinksDao.insert(drinks.map { drink ->
			drink.toEntity()
		})
	}
}

object CouldNotFindDrink : Throwable("Couldn't find drink info")
