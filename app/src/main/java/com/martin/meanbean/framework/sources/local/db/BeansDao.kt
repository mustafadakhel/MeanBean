package com.martin.meanbean.framework.sources.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.martin.meanbean.framework.sources.local.entities.BeanEntity

@Dao
interface BeansDao {
	@Query("SELECT * FROM bean")
	suspend fun getBeans(): List<BeanEntity>

	@Query("SELECT * FROM bean WHERE id = :id")
	suspend fun getBean(id: Int): BeanEntity?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(item: BeanEntity)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(items: List<BeanEntity>)
}