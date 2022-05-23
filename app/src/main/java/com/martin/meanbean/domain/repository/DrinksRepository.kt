package com.martin.meanbean.domain.repository

import com.martin.meanbean.domain.models.Drink

interface DrinksRepository {
	suspend fun getDrinks(): List<Drink>
	suspend fun getDrink(drinkId: Int): Drink?
}