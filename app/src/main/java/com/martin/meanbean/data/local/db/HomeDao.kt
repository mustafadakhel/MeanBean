package com.martin.meanbean.data.local.db

import androidx.room.Dao
import androidx.room.Query
import com.martin.meanbean.domain.entities.BeanEntity
import com.martin.meanbean.domain.entities.DrinkEntity

@Dao
interface HomeDao {
	@Query("SELECT * FROM BEAN")
	fun getBeans(): List<BeanEntity>

	@Query("SELECT * FROM BEAN WHERE id = :id")
	fun getBean(id: Int): BeanEntity

	@Query("SELECT * FROM BEAN")
	fun getDrinks(): List<DrinkEntity>

	@Query("SELECT * FROM DRINK WHERE id = :id")
	fun getDrink(id: Int): DrinkEntity
}