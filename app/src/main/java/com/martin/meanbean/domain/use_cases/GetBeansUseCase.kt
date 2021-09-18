package com.martin.meanbean.domain.use_cases

import com.martin.meanbean.data.repository.MeanBeanRepository
import com.martin.meanbean.domain.entities.BeanEntity
import com.martin.meanbean.domain.entities.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBeansUseCase @Inject constructor(private val meanBeanRepository: MeanBeanRepository) {
	suspend operator fun invoke(): Flow<Result<List<BeanEntity>>> {
		return meanBeanRepository.getBeanTypes()
	}
}