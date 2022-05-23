package com.martin.meanbean.data.sources.implementation.local.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseDao<Entity> {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(item: Entity)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(items: List<Entity>)
}