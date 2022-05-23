package com.martin.meanbean.domain.repository

import com.martin.meanbean.domain.models.HomeFeedList

interface HomeRepository {
	suspend fun homeFeed(): List<HomeFeedList>
}