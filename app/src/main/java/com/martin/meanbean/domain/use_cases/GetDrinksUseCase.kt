package com.martin.meanbean.domain.use_cases

import com.martin.meanbean.data.repository.MeanBeanRepository
import com.martin.meanbean.domain.entities.DrinkEntity
import com.martin.meanbean.domain.entities.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDrinksUseCase @Inject constructor(private val meanBeanRepository: MeanBeanRepository) {
	suspend operator fun invoke(): Flow<Result<List<DrinkEntity>>> {
		return meanBeanRepository.getDrinkTypes()
	}
}