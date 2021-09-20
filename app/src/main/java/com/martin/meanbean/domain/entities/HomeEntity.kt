package com.martin.meanbean.domain.entities

data class HomeEntity(
	val title: String = "",
	val items: List<HomeSubEntity>,
	val type: HomeEntityType
) {
	sealed class HomeEntityType {
		object Beans : HomeEntityType()
		object Drinks : HomeEntityType()
	}
}