package com.martin.meanbean.data.repository

import com.martin.meanbean.data.sources.contract.BeansSource
import com.martin.meanbean.data.sources.contract.MutableBeansSource
import com.martin.meanbean.domain.models.Bean
import com.martin.meanbean.domain.repository.BeansRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultBeansRepository @Inject constructor(
	private val localBeansSource: MutableBeansSource,
	private val remoteBeansSource: BeansSource,
) : BeansRepository {
	override suspend fun getBeans(): List<Bean> = runCatching {
		remoteBeansSource.getBeans()
	}.onSuccess {
		it.forEach { drink -> localBeansSource.addBean(drink) }
	}.getOrElse {
		localBeansSource.getBeans()
	}

	override suspend fun getBean(beanId: Int) = localBeansSource.getBean(beanId)
}