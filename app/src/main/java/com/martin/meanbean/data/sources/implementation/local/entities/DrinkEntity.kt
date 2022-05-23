package com.martin.meanbean.data.sources.implementation.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drink")
data class DrinkEntity(
	@PrimaryKey
	val id: Int,
	var name: String = "",
	var description: String = "",
	var image: String = "",
	var ratio: String = "",
	var cup: String = "",
)