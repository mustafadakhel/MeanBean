package com.martin.meanbean.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.martin.meanbean.domain.entities.BeanEntity
import com.martin.meanbean.domain.entities.DrinkEntity

@Database(entities = [BeanEntity::class, DrinkEntity::class], version = 1)
abstract class HomeDB : RoomDatabase() {
	abstract val homeDao: HomeDao
}

