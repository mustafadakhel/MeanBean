package com.martin.meanbean.data.repository

import com.martin.meanbean.data.local.db.BeansDao
import com.martin.meanbean.data.local.db.DrinksDao
import com.martin.meanbean.data.remote.network.MeanBeanApi
import com.martin.meanbean.domain.mapper.Mapper
import com.martin.meanbean.utils.resultFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MeanBeanRepository @Inject constructor(
	private val meanBeanApi: MeanBeanApi,
	private val beansDao: BeansDao,
	private val drinksDao: DrinksDao,
	private val mapper: Mapper
) {
	fun getBeanTypes() = resultFlow(
		network = { meanBeanApi.getBeanTypes().beans.map(mapper::mapBean) },
		cached = { beansDao.getBeans() },
		updateCache = { it.forEach { bean -> beansDao.insert(bean) } }
	)

	fun getDrinkTypes() = resultFlow(
		network = { meanBeanApi.getDrinkTypes().drinks.map(mapper::mapDrink) },
		cached = { drinksDao.getDrinks() },
		updateCache = { it.forEach { drink -> drinksDao.insert(drink) } }
	)
}

