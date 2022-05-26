package com.martin.meanbean.framework.sources.remote.dto

import com.martin.meanbean.domain.models.Drink

data class DrinkDto(
	val id: Int = -1,
	val name: String = "",
	val desc: String = "",
	val image: String = "",
	val ratio: String = "",
	val cup: String = "",
)

fun DrinkDto.toDrink() = Drink(
	id = id,
	name = name,
	image = image,
	description = desc,
	ratio = ratio,
	cup = cup
)