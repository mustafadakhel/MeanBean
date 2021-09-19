package com.martin.meanbean.data.repository

import com.martin.meanbean.data.remote.network.MeanBeanApi
import com.martin.meanbean.domain.mapper.Mapper
import com.martin.meanbean.utils.resultFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MeanBeanRepository @Inject constructor(
	private val meanBeanApi: MeanBeanApi,
	private val mapper: Mapper
) {
	suspend fun getBeanTypes() = resultFlow {
		meanBeanApi.getBeanTypes().beans.map(mapper::mapBean)
	}

	suspend fun getDrinkTypes() = resultFlow {
		meanBeanApi.getDrinkTypes().drinks.map(mapper::mapDrink)
	}
}

