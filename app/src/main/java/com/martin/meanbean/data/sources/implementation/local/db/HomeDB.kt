package com.martin.meanbean.data.sources.implementation.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.martin.meanbean.data.sources.implementation.local.entities.BeanEntity
import com.martin.meanbean.data.sources.implementation.local.entities.DrinkEntity

@Database(entities = [BeanEntity::class, DrinkEntity::class], version = 1)
abstract class HomeDB : RoomDatabase() {
	abstract val beansDao: BeansDao
	abstract val drinksDao: DrinksDao
}