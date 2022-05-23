package com.martin.meanbean.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Drink(
	override val id: Int,
	override var name: String = "",
	var description: String = "",
	override var image: String = "",
	var ratio: String = "",
	var cup: String = "",
) : Parcelable, HomeFeedItem {
	fun toHomeSub(): HomeSubSegment {
		return HomeSubSegment(
			title = this.name,
			image = this.image,
			id = id
		)
	}
}

@JvmName("toHomeDrink")
fun List<Drink>.toHomeSegment(): HomeSegment {
	return HomeSegment(
		title = "Drinks",
		items = map { it.toHomeSub() },
		type = HomeSegment.HomeSegmentType.Drinks
	)
}