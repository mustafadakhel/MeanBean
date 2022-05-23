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
	feed.toSegment()
}

private fun HomeFeedList.toSegment(): HomeSegment {
	val items = items.map { it.toHomeSubSegment() }
	val type = when (type) {
		HomeFeedList.HomeFeedListType.Beans -> HomeSegment.HomeSegmentType.Beans
		HomeFeedList.HomeFeedListType.Drinks -> HomeSegment.HomeSegmentType.Drinks
	}
	return HomeSegment(title, items, type)
}

fun HomeFeedItem.toHomeSubSegment() = HomeSubSegment(id, name, image)

