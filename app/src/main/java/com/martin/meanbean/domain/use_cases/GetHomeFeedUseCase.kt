package com.martin.meanbean.domain.use_cases

import com.martin.meanbean.data.repository.MeanBeanRepository
import com.martin.meanbean.domain.entities.HomeEntity
import com.martin.meanbean.domain.entities.Result
import com.martin.meanbean.domain.entities.toHomeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.zip
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetHomeFeedUseCase @Inject constructor(private val meanBeanRepository: MeanBeanRepository) {

	operator fun invoke(): Flow<MutableList<Result<HomeEntity>>> {

		val beanTypesFlow = meanBeanRepository.getBeanTypes()
		val drinkTypesFlow = meanBeanRepository.getDrinkTypes()

		return beanTypesFlow.zip(drinkTypesFlow) { beansResult, drinksResult ->
			mutableListOf<Result<HomeEntity>>().apply {
				val beanEntity = beansResult.data?.toHomeEntity()
				val drinksEntity = drinksResult.data?.toHomeEntity()
				add(beansResult.changeData(data = beanEntity))
				add(drinksResult.changeData(data = drinksEntity))
			}
		}
	}

}

