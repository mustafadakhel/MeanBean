package com.martin.meanbean.ui.drink_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.meanbean.domain.models.Drink
import com.martin.meanbean.domain.use_cases.GetDrinkUseCase
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
class DrinkPageViewModel @Inject constructor(
	private val getDrinkUseCase: GetDrinkUseCase,
) : ViewModel() {

	sealed interface DrinkPageEras : TimeMachine.Eras {
		object Loading : DrinkPageEras
		object Loaded : DrinkPageEras
		data class Failure(val throwable: Throwable) : DrinkPageEras
	}

	private val _drinkTimeMachine: ControllableTimeMachine<DrinkPageEras> =
		controllableTimeMachine(DrinkPageEras.Loading)
	val drinkTimeMachine: TimeMachine<DrinkPageEras> = _drinkTimeMachine.asTimeMachine()
	private val _drinkListFlow = MutableStateFlow<Drink?>(null)
	val drinkFlow = _drinkListFlow.asStateFlow()

	fun fetch(drinkId: Int?) = viewModelScope.launch {
		runCatching {
			_drinkTimeMachine.newDestination(DrinkPageEras.Loading)
			getDrinkUseCase(drinkId).also {
				_drinkListFlow.emit(it)
			}
		}.onSuccess {
			_drinkTimeMachine.newDestination(DrinkPageEras.Loaded)
		}.onFailure {
			_drinkTimeMachine.newDestination(DrinkPageEras.Failure(it))
		}
	}
}