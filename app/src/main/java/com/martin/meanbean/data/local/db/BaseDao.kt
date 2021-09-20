package com.martin.meanbean.data.local.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseDao<Entity> {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(item: Entity)
}