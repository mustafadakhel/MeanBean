package com.martin.meanbean.ui.bean_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.meanbean.domain.models.Bean
import com.martin.meanbean.domain.use_cases.GetBeanUseCase
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
class BeanPageViewModel @Inject constructor(
	private val getBeanUseCase: GetBeanUseCase,
) : ViewModel() {

	sealed interface BeanPageEras : TimeMachine.Eras {
		object Loading : BeanPageEras
		object Loaded : BeanPageEras
		data class Failure(val throwable: Throwable) : BeanPageEras
	}

	private val _beanTimeMachine: ControllableTimeMachine<BeanPageEras> =
		controllableTimeMachine(BeanPageEras.Loading)
	val beanTimeMachine: TimeMachine<BeanPageEras> = _beanTimeMachine.asTimeMachine()
	private val _beanListFlow = MutableStateFlow<Bean?>(null)
	val beanFlow = _beanListFlow.asStateFlow()

	fun fetch(beanId: Int?) = viewModelScope.launch {
		runCatching {
			_beanTimeMachine.newDestination(BeanPageEras.Loading)
			getBeanUseCase(beanId).also {
				_beanListFlow.emit(it)
			}
		}.onSuccess {
			_beanTimeMachine.newDestination(BeanPageEras.Loaded)
		}.onFailure {
			_beanTimeMachine.newDestination(BeanPageEras.Failure(it))
		}
	}
}