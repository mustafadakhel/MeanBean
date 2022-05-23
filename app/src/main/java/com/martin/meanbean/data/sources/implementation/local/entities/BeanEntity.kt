package com.martin.meanbean.data.sources.implementation.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bean")
data class BeanEntity(
	@PrimaryKey
	val id: Int,
	var name: String = "",
	var image: String = "",
	var description: String = "",
)
