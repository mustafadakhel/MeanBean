package com.martin.meanbean.domain.use_cases

import com.martin.meanbean.data.repository.MeanBeanRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDrinksUseCase @Inject constructor(private val meanBeanRepository: MeanBeanRepository) {
	suspend operator fun invoke() = meanBeanRepository.getDrinkTypes()
}