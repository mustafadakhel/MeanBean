package com.martin.meanbean.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.martin.meanbean.domain.entities.Wrap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException



@Composable
fun <T> Flow<T>.asLifecycleAwareState(lifecycleOwner: LifecycleOwner, initialState: T? = null) =
	lifecycleAwareState(lifecycleOwner, this, initialState)

@Composable
fun <T> lifecycleAwareState(
	lifecycleOwner: LifecycleOwner,
	flow: Flow<T>,
	initialState: T
): State<T> {
	val lifecycleAwareStateFlow = remember(flow, lifecycleOwner) {
		flow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
	}
	return lifecycleAwareStateFlow.collectAsState(initialState)
}
