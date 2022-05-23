package com.martin.meanbean.ui.bean_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.meanbean.domain.models.Bean
import com.martin.meanbean.domain.use_cases.GetBeanUseCase
import com.martin.meanbean.ui.common.DefaultTimeMachineDelegate
import com.martin.meanbean.ui.common.TimeMachineDelegate
import com.martin.meanbean.utils.TimeMachine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeanPageViewModel @Inject constructor(
	private val getBeanUseCase: GetBeanUseCase,
) : ViewModel(),
	TimeMachineDelegate<BeanPageViewModel.Eras>
	by DefaultTimeMachineDelegate(Eras.Loading) {

	sealed interface Eras : TimeMachine.Eras {
		object Loading : Eras
		object Loaded : Eras
		data class Failure(val throwable: Throwable) : Eras
	}

	private val _beanListFlow = MutableStateFlow<Bean?>(null)
	val beanFlow = _beanListFlow.asStateFlow()

	fun fetch(beanId: Int?) = viewModelScope.launch {
		runCatching {
			timeMachine.newDestination(Eras.Loading)
			getBeanUseCase(beanId).also {
				_beanListFlow.emit(it)
			}
		}.onSuccess {
			timeMachine.newDestination(Eras.Loaded)
		}.onFailure {
			timeMachine.newDestination(Eras.Failure(it))
		}
	}
}