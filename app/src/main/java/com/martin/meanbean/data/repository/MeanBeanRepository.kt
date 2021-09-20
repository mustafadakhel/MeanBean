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
	fun getBeanTypes() = resultFlow(
		network = { meanBeanApi.getBeanTypes().beans.map(mapper::mapBean) },
		db = {

		}
	)

	fun getDrinkTypes() = resultFlow(
		network = {
			meanBeanApi.getDrinkTypes().drinks.map(mapper::mapDrink)
		}
	)
}

