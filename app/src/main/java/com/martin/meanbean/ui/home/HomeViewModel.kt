package com.martin.meanbean.ui.home

import androidx.lifecycle.ViewModel
import com.martin.meanbean.domain.use_cases.GetHomeFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	getHomeFeedUseCase: GetHomeFeedUseCase,
) : ViewModel() {
	val homeFeed = getHomeFeedUseCase()
}