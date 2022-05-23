package com.martin.meanbean.domain.use_cases

import com.martin.meanbean.domain.repository.DrinksRepository
import javax.inject.Inject

class GetDrinkUseCase @Inject constructor(private val drinksRepository: DrinksRepository) {

	suspend operator fun invoke(drinkId: Int) = drinksRepository.getDrink(drinkId)

}