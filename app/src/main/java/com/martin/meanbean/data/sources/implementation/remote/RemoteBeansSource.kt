package com.martin.meanbean.data.sources.implementation.remote

import com.martin.meanbean.data.sources.contract.BeansSource
import com.martin.meanbean.domain.models.Bean
import com.martin.meanbean.data.sources.implementation.remote.retrofit.MeanBeanApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteBeansSource @Inject constructor(private val meanBeanApi: MeanBeanApi) : BeansSource {
	override suspend fun getBeans() = meanBeanApi.getBeans().beans.map {
		Bean(
			id = it.id,
			name = it.name,
			image = it.image,
			description = it.desc
		)
	}

	override suspend fun getBean(beanId: Int) = meanBeanApi.getBeans().beans.find {
		it.id == beanId
	}?.let {
		Bean(
			id = it.id,
			name = it.name,
			image = it.image,
			description = it.desc
		)
	}
}