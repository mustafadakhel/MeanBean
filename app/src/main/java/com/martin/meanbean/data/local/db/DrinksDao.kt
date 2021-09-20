package com.martin.meanbean.data.local.db

import androidx.room.Dao
import androidx.room.Query
import com.martin.meanbean.domain.entities.DrinkEntity

@Dao
interface DrinksDao : BaseDao<DrinkEntity> {
	@Query("SELECT * FROM DRINK")
	suspend fun getDrinks(): List<DrinkEntity>

	@Query("SELECT * FROM DRINK WHERE id = :id")
	suspend fun getDrink(id: Int): DrinkEntity
}