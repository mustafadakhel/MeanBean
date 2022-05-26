package com.martin.meanbean.framework.sources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.martin.meanbean.domain.models.Drink

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

fun DrinkEntity.toDrink() = Drink(
	id = id,
	name = name,
	image = image,
	description = description,
	ratio = ratio,
	cup = cup
)

fun Drink.toEntity() = DrinkEntity(
	id = id,
	name = name,
	image = image,
	description = description,
	ratio = ratio,
	cup = cup
)