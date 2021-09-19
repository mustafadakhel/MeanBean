package com.martin.meanbean.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BeanEntity(
	var name: String = "",
	var image: String = "",
	var description: String = "",
) : Parcelable {
	fun toHomeSubEntity(): HomeSubEntity {
		return HomeSubEntity(
			title = this.name,
			image = this.image
		)
	}
}

@JvmName("toHomeEntityBeanEntity")
fun List<BeanEntity>.toHomeEntity(): HomeEntity {
	return HomeEntity(
		title = "Beans",
		items = map { it.toHomeSubEntity() })
}
