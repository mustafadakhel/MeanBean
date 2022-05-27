package com.martin.meanbean.framework.ui.drink_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.meanbean.domain.models.Drink
import com.martin.meanbean.domain.use_cases.GetDrinkUseCase
import com.martin.meanbean.framework.ui.common.DefaultTimeMachineDelegate
import com.martin.meanbean.framework.ui.common.TimeMachineDelegate
import com.martin.meanbean.framework.utils.TimeMachine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkPageViewModel @Inject constructor(
	private val getDrinkUseCase: GetDrinkUseCase,
) : ViewModel(),
	TimeMachineDelegate<DrinkPageViewModel.Eras>
	by DefaultTimeMachineDelegate(Eras.Loading) {

	sealed interface Eras : TimeMachine.Eras {
		object Loading : Eras
		object Loaded : Eras
		data class Failure(val throwable: Throwable) : Eras
	}

	private val _drinkListFlow = MutableStateFlow<Drink?>(null)
	val drinkFlow = _drinkListFlow.asStateFlow()

	fun fetch(drinkId: Int?) = viewModelScope.launch {
		runCatching {
			timeMachine.newDestination(Eras.Loading)
			getDrinkUseCase(drinkId).also {
				_drinkListFlow.emit(it)
			}
		}.onSuccess {
			timeMachine.newDestination(Eras.Loaded)
		}.onFailure {
			timeMachine.newDestination(Eras.Failure(it))
		}
	}
}