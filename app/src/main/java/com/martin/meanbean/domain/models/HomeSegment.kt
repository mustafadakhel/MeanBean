package com.martin.meanbean.domain.models

data class HomeSegment(
	val title: String = "",
	val items: List<HomeSubSegment>,
	val type: HomeSegmentType
) {
	sealed class HomeSegmentType {
		object Beans : HomeSegmentType()
		object Drinks : HomeSegmentType()
	}
}