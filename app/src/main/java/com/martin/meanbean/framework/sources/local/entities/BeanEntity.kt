package com.martin.meanbean.framework.sources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.martin.meanbean.domain.models.Bean

@Entity(tableName = "bean")
data class BeanEntity(
	@PrimaryKey
	val id: Int,
	var name: String = "",
	var image: String = "",
	var description: String = "",
)

fun BeanEntity.toBean() = Bean(
	id = id,
	name = name,
	image = image,
	description = description
)

fun Bean.toEntity() = BeanEntity(
	id = id,
	name = name,
	image = image,
	description = description
)