package com.martin.meanbean.data.sources.implementation.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.martin.meanbean.data.sources.implementation.local.entities.DrinkEntity

@Dao
interface DrinksDao {
	@Query("SELECT * FROM drink")
	suspend fun getDrinks(): List<DrinkEntity>

	@Query("SELECT * FROM drink WHERE id = :id")
	suspend fun getDrink(id: Int): DrinkEntity?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(item: DrinkEntity)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(items: List<DrinkEntity>)
}