package com.martin.meanbean.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bean(
	override val id: Int,
	override var name: String = "",
	override var image: String = "",
	var description: String = "",
) : Parcelable, HomeFeedItem {
	fun toHomeSub(): HomeSubSegment {
		return HomeSubSegment(
			id = this.id,
			title = this.name,
			image = this.image
		)
	}
}

@JvmName("toHomeBean")
fun List<Bean>.toHomeSegment(): HomeSegment {
	return HomeSegment(
		title = "Beans",
		items = map { it.toHomeSub() },
		type = HomeSegment.HomeSegmentType.Beans
	)
}
