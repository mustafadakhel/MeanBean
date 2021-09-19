package com.martin.meanbean.domain.entities

data class BeanEntity(
	val name: String = "",
	val image: String = "",
	val description: String = "",
) {
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
