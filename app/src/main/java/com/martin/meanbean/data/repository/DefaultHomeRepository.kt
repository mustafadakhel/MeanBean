package com.martin.meanbean.data.repository

import com.martin.meanbean.domain.models.HomeFeedList
import com.martin.meanbean.domain.repository.BeansRepository
import com.martin.meanbean.domain.repository.DrinksRepository
import com.martin.meanbean.domain.repository.HomeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultHomeRepository @Inject constructor(
	private val beansRepository: BeansRepository,
	private val drinksRepository: DrinksRepository
) : HomeRepository {
	override suspend fun homeFeed() = mutableListOf<HomeFeedList>().apply {
		val beans = HomeFeedList(
			"Beans",
			beansRepository.getBeans(),
			HomeFeedList.HomeFeedListType.Beans
		)
		val drinks = HomeFeedList(
			"Drinks",
			drinksRepository.getDrinks(),
			HomeFeedList.HomeFeedListType.Drinks
		)
		add(beans)
		add(drinks)
	}
}