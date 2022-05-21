package com.martin.meanbean.data.repository

import com.martin.meanbean.data.local.db.BeansDao
import com.martin.meanbean.data.local.db.DrinksDao
import com.martin.meanbean.data.remote.network.MeanBeanApi
import com.martin.meanbean.domain.entities.HomeEntity
import com.martin.meanbean.domain.entities.toHomeEntity
import com.martin.meanbean.domain.mapper.Mapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MeanBeanRepository @Inject constructor(
	private val meanBeanApi: MeanBeanApi,
	private val beansDao: BeansDao,
	private val drinksDao: DrinksDao,
	private val mapper: Mapper
) {

	suspend fun beanTypes() = runCatching {
		meanBeanApi.getBeanTypes().beans.map(mapper::mapBean)
	}.onSuccess {
		it.forEach { bean -> beansDao.insert(bean) }
	}.getOrElse {
		beansDao.getBeans()
	}

	suspend fun drinkTypes() = runCatching {
		meanBeanApi.getDrinkTypes().drinks.map(mapper::mapDrink)
	}.onSuccess {
		it.forEach { drink -> drinksDao.insert(drink) }
	}.getOrElse {
		drinksDao.getDrinks()
	}

	suspend fun homeList() = mutableListOf<HomeEntity>().apply {
		val beanEntity = beanTypes().toHomeEntity()
		val drinksEntity = drinkTypes().toHomeEntity()
		add(beanEntity)
		add(drinksEntity)
	}
}

