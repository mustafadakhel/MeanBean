package com.martin.meanbean.domain.entities

data class DrinkEntity(
	val name: String = "",
	val description: String = "",
	val image: String = "",
	val ratio: String = "",
	val cup: String = "",
) {
	fun toHomeSubEntity(): HomeSubEntity {
		return HomeSubEntity(
			title = this.name,
			image = this.image
		)
	}
}

@JvmName("toHomeEntityDrinkEntity")
fun List<DrinkEntity>.toHomeEntity(): HomeEntity {
	return HomeEntity(
		title = "Drinks",
		items = map { it.toHomeSubEntity() })
}