package com.martin.meanbean.domain.models

data class HomeFeedList(
	val title: String = "",
	val items: List<HomeFeedItem>,
	val type: HomeFeedListType
) {
	sealed class HomeFeedListType {
		object Beans : HomeFeedListType()
		object Drinks : HomeFeedListType()
	}
}

interface HomeFeedItem {
	val id: Int
	var name: String
	var image: String
}

fun List<HomeFeedList>.toHomeSegments() = map { feed ->
	val items = feed.items.map { it.toHomeSubSegment() }
	val type = when (feed.type) {
		HomeFeedList.HomeFeedListType.Beans -> HomeSegment.HomeSegmentType.Beans
		HomeFeedList.HomeFeedListType.Drinks -> HomeSegment.HomeSegmentType.Drinks
	}
	HomeSegment(feed.title, items, type)
}

fun HomeFeedItem.toHomeSubSegment() = HomeSubSegment(id, name, image)

