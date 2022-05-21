package com.martin.meanbean.domain.use_cases

import com.martin.meanbean.data.repository.MeanBeanRepository
import com.martin.meanbean.domain.entities.HomeEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetHomeFeedUseCase @Inject constructor(private val meanBeanRepository: MeanBeanRepository) {

	suspend operator fun invoke(): MutableList<HomeEntity> = meanBeanRepository.homeList()

}

