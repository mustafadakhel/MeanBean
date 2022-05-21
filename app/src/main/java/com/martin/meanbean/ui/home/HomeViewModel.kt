package com.martin.meanbean.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.meanbean.domain.entities.HomeEntity
import com.martin.meanbean.domain.use_cases.GetHomeFeedUseCase
import com.martin.meanbean.utils.ControllableTimeMachine
import com.martin.meanbean.utils.TimeMachine
import com.martin.meanbean.utils.asTimeMachine
import com.martin.meanbean.utils.controllableTimeMachine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val getHomeFeedUseCase: GetHomeFeedUseCase,
) : ViewModel() {
	sealed interface HomeEra : TimeMachine.Era {
		object Loading : HomeEra
		object Loaded : HomeEra
		data class Failure(val throwable: Throwable) : HomeEra
	}

	private val _homeTimeMachine: ControllableTimeMachine<HomeEra> =
		controllableTimeMachine(HomeEra.Loading)
	val homeTimeMachine: TimeMachine<HomeEra> = _homeTimeMachine.asTimeMachine()
	private val _homeListFlow = MutableStateFlow<List<HomeEntity>>(listOf())
	val homeListFlow = _homeListFlow.asStateFlow()

	init {
		fetch()
	}

	fun fetch() = viewModelScope.launch {
		runCatching {
			_homeTimeMachine.newDestination(HomeEra.Loading)
			getHomeFeedUseCase().also {
				_homeListFlow.emit(it)
			}
		}.onSuccess {
			_homeTimeMachine.newDestination(HomeEra.Loaded)
		}.onFailure {
			_homeTimeMachine.newDestination(HomeEra.Failure(it))
		}
	}
}


