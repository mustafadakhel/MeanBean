package com.martin.meanbean.domain.use_cases

import com.martin.meanbean.domain.repository.HomeRepository
import javax.inject.Inject

class GetHomeFeedUseCase @Inject constructor(private val homeRepository: HomeRepository) {

	suspend operator fun invoke() = homeRepository.homeFeed()

}
