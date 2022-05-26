package com.martin.meanbean.data.repository

import com.martin.meanbean.data.sources.DrinksSource
import com.martin.meanbean.data.sources.MutableDrinksSource
import com.martin.meanbean.domain.models.Drink
import com.martin.meanbean.domain.repository.DrinksRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultDrinksRepository @Inject constructor(
	private val localDrinksSource: MutableDrinksSource,
	private val remoteDrinksSource: DrinksSource,
) : DrinksRepository {
	override suspend fun getDrinks(): List<Drink> = runCatching {
		remoteDrinksSource.getDrinks()
	}.onSuccess { drinks ->
		localDrinksSource.addDrinks(drinks)
	}.getOrElse {
		localDrinksSource.getDrinks()
	}

	override suspend fun getDrink(drinkId: Int) = localDrinksSource.getDrink(drinkId)
}