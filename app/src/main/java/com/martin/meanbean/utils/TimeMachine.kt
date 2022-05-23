package com.martin.meanbean.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private class DefaultTimeMachine<T : TimeMachine.Eras>(initialEvent: T) :
	ControllableTimeMachine<T> {

	private var eventsFlow: MutableStateFlow<T>? = null

	override fun flowEras(): StateFlow<T> {
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

interface ControllableTimeMachine<T : TimeMachine.Eras> : TimeMachine<T> {
	suspend fun newDestination(event: T)
}

interface TimeMachine<T : TimeMachine.Eras> {
	interface Eras

	fun flowEras(): StateFlow<T>
	val currentEvent: T
}

fun <T : TimeMachine.Eras> controllableTimeMachine(initialEra: T): ControllableTimeMachine<T> =
	DefaultTimeMachine(initialEra)

fun <T : TimeMachine.Eras> ControllableTimeMachine<T>.asTimeMachine(): TimeMachine<T> = this