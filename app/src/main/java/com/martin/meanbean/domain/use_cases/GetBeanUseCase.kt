package com.martin.meanbean.domain.use_cases

import com.martin.meanbean.domain.repository.BeansRepository
import javax.inject.Inject

class GetBeanUseCase @Inject constructor(private val beansRepository: BeansRepository) {

	suspend operator fun invoke(beanId: Int?) =
		beanId?.let { beansRepository.getBean(beanId) } ?: throw NullBeanId

}

object NullBeanId : Throwable("a null bean id was provided")