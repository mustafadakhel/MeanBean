package com.martin.meanbean.domain.use_cases

import com.martin.meanbean.data.repository.MeanBeanRepository
import javax.inject.Inject

class GetBeanUseCase @Inject constructor(private val meanBeanRepository: MeanBeanRepository) {

	suspend operator fun invoke(beanId: Int) = meanBeanRepository.getBean(beanId)

}