package com.martin.meanbean.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DrinkEntity(
	var name: String = "",
	var description: String = "",
	var image: String = "",
	var ratio: String = "",
	var cup: String = "",
) : Parcelable {
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