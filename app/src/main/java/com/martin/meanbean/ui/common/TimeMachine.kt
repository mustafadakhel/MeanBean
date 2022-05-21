package com.martin.meanbean.ui.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private class DefaultTimeMachine<T : TimeMachine.Era>(initialEvent: T) :
	ControllableTimeMachine<T> {

	private var eventsFlow: MutableStateFlow<T>? = null

	override fun flow(): StateFlow<T> {
		if (eventsFlow == null)
			eventsFlow = MutableStateFlow(currentEvent)
		return eventsFlow!!
	}

	override var currentEvent: T = initialEvent
		private set

	override suspend fun newDestination(event: T) {
		currentEvent = event
		eventsFlow?.emit(currentEvent)
	}
}

interface ControllableTimeMachine<T : TimeMachine.Era> : TimeMachine<T> {
	suspend fun newDestination(event: T)
}

interface TimeMachine<T : TimeMachine.Era> {
	interface Era

	fun flow(): StateFlow<T>
	val currentEvent: T
}

fun <T : TimeMachine.Era> controllableTimeMachine(initialEra: T): ControllableTimeMachine<T> =
	DefaultTimeMachine(initialEra)

fun <T : TimeMachine.Era> ControllableTimeMachine<T>.asTimeMachine(): TimeMachine<T> = this