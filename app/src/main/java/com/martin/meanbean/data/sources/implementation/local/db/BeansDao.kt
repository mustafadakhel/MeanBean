package com.martin.meanbean.data.sources.implementation.local.db

import androidx.room.Dao
import androidx.room.Query
import com.martin.meanbean.data.sources.implementation.local.entities.BeanEntity

@Dao
interface BeansDao : BaseDao<BeanEntity> {
	@Query("SELECT * FROM bean")
	suspend fun getBeans(): List<BeanEntity>

	@Query("SELECT * FROM bean WHERE id = :id")
	suspend fun getBean(id: Int): BeanEntity?
}