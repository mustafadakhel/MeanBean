package com.martin.meanbean.framework.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.meanbean.domain.models.HomeSegment
import com.martin.meanbean.domain.models.toHomeSegments
import com.martin.meanbean.domain.use_cases.GetHomeFeedUseCase
import com.martin.meanbean.framework.utils.ControllableTimeMachine
import com.martin.meanbean.framework.utils.TimeMachine
import com.martin.meanbean.framework.utils.asTimeMachine
import com.martin.meanbean.framework.utils.controllableTimeMachine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val getHomeFeedUseCase: GetHomeFeedUseCase,
) : ViewModel() {
	sealed interface HomeEras : TimeMachine.Eras {
		object Loading : HomeEras
		object Loaded : HomeEras
		data class Failure(val throwable: Throwable) : HomeEras
	}

	private val _homeTimeMachine: ControllableTimeMachine<HomeEras> =
		controllableTimeMachine(HomeEras.Loading)
	val homeTimeMachine: TimeMachine<HomeEras> = _homeTimeMachine.asTimeMachine()
	private val _homeSegmentListFlow = MutableStateFlow<List<HomeSegment>>(listOf())
	val homeListFlow = _homeSegmentListFlow.asStateFlow()

	init {
		fetch()
	}

	fun fetch() = viewModelScope.launch {
		runCatching {
			_homeTimeMachine.newDestination(HomeEras.Loading)
			getHomeFeedUseCase().toHomeSegments().also {
				_homeSegmentListFlow.emit(it)
			}
		}.onSuccess {
			_homeTimeMachine.newDestination(HomeEras.Loaded)
		}.onFailure {
			_homeTimeMachine.newDestination(HomeEras.Failure(it))
		}
	}
}

