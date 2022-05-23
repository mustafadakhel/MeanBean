package com.martin.meanbean.data.sources.implementation.local

import com.martin.meanbean.data.sources.contract.MutableBeansSource
import com.martin.meanbean.data.sources.implementation.local.db.BeansDao
import com.martin.meanbean.data.sources.implementation.local.entities.BeanEntity
import com.martin.meanbean.data.sources.implementation.local.entities.toBean
import com.martin.meanbean.domain.models.Bean
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalBeansSource @Inject constructor(private val beansDao: BeansDao) : MutableBeansSource {
	override suspend fun getBeans() = beansDao.getBeans().map { entity ->
		entity.toBean()
	}

	override suspend fun getBean(beanId: Int) = beansDao.getBean(beanId)?.toBean() ?: throw CouldNotFindBean

	override suspend fun addBean(bean: Bean) {
		beansDao.insert(BeanEntity(bean.id, bean.name, bean.image, bean.description))
	}

	override suspend fun addBeans(beans: List<Bean>) {
		beansDao.insert(beans.map { bean ->
			BeanEntity(bean.id, bean.name, bean.image, bean.description)
		})
	}
}

object CouldNotFindBean : Throwable("Couldn't find bean info")
