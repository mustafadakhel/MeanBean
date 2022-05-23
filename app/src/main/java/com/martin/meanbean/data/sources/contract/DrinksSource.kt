package com.martin.meanbean.data.sources.contract

import com.martin.meanbean.domain.models.Drink

interface DrinksSource {
	suspend fun getDrinks(): List<Drink>
	suspend fun getDrink(drinkId: Int): Drink?
}
