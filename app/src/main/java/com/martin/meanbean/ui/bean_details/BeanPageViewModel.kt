package com.martin.meanbean.ui.bean_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.meanbean.domain.entities.BeanEntity
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

	sealed interface BeanPageEra : TimeMachine.Era {
		object Loading : BeanPageEra
		object Loaded : BeanPageEra
		data class Failure(val throwable: Throwable) : BeanPageEra
	}

	private val _beanTimeMachine: ControllableTimeMachine<BeanPageEra> =
		controllableTimeMachine(BeanPageEra.Loading)
	val beanTimeMachine: TimeMachine<BeanPageEra> = _beanTimeMachine.asTimeMachine()
	private val _beanListFlow = MutableStateFlow<BeanEntity?>(null)
	val beanFlow = _beanListFlow.asStateFlow()

	fun fetch(beanId: Int?) = viewModelScope.launch {
		runCatching {
			_beanTimeMachine.newDestination(BeanPageEra.Loading)
			beanId?.let {
				getBeanUseCase(beanId)?.also {
					_beanListFlow.emit(it)
				} ?: throw CouldNotFindBean
			} ?: throw NullBeanId
		}.onSuccess {
			_beanTimeMachine.newDestination(BeanPageEra.Loaded)
		}.onFailure {
			_beanTimeMachine.newDestination(BeanPageEra.Failure(it))
		}
	}
}

object NullBeanId : Throwable("a null bean id was provided")
object CouldNotFindBean : Throwable("Couldn't find bean info")
