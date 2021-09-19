package com.martin.meanbean.domain.use_cases

import com.martin.meanbean.data.repository.MeanBeanRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBeansUseCase @Inject constructor(private val meanBeanRepository: MeanBeanRepository) {
	suspend operator fun invoke() = meanBeanRepository.getBeanTypes()
}