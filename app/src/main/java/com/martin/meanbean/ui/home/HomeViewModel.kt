package com.martin.meanbean.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.meanbean.domain.entities.HomeEntity
import com.martin.meanbean.domain.entities.Wrap
import com.martin.meanbean.domain.use_cases.GetHomeFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val getHomeFeedUseCase: GetHomeFeedUseCase,
) : ViewModel() {

	private val _wrappedHomeListFlow = MutableStateFlow<Wrap<List<HomeEntity>>>(Wrap.loading())
	val wrappedHomeListFlow = _wrappedHomeListFlow.asStateFlow()

	init {
		fetch()
	}

	fun fetch() {
		viewModelScope.launch {
			getHomeFeedUseCase().also {
				_wrappedHomeListFlow.emit(Wrap.success(it))
			}
		}
	}
}