package com.martin.meanbean.data.local.db

import androidx.room.Dao
import androidx.room.Query
import com.martin.meanbean.domain.entities.BeanEntity

@Dao
interface BeansDao : BaseDao<BeanEntity> {
	@Query("SELECT * FROM BEAN")
	suspend fun getBeans(): List<BeanEntity>

	@Query("SELECT * FROM BEAN WHERE id = :id")
	suspend fun getBean(id: Int): BeanEntity
}