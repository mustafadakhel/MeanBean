package com.martin.meanbean.data.sources.contract

import com.martin.meanbean.domain.models.Drink

interface MutableDrinksSource {
	suspend fun getDrinks(): List<Drink>
	suspend fun getDrink(drinkId: Int): Drink?
	suspend fun addDrink(drink: Drink)
	suspend fun addDrinks(drinks: List<Drink>)
}