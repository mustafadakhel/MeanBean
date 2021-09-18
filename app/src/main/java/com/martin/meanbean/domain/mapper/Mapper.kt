package com.martin.meanbean.domain.mapper

import com.martin.meanbean.data.remote.dto.BeanDto
import com.martin.meanbean.data.remote.dto.DrinkDto
import com.martin.meanbean.domain.entities.BeanEntity
import com.martin.meanbean.domain.entities.DrinkEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Mapper @Inject constructor(
	private val beanMapper: BeanMapper,
	private val drinkMapper: DrinkMapper
) {
	fun mapBean(drink: BeanDto): BeanEntity {
		return beanMapper.mapDtoToDomain(drink)
	}

	fun mapDrink(drink: DrinkDto): DrinkEntity {
		return drinkMapper.mapDtoToDomain(drink)
	}
}