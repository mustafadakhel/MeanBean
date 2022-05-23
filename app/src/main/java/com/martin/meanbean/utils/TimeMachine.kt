package com.martin.meanbean.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private class DefaultTimeMachine<T : TimeMachine.Eras>(initialEra: T) :
	ControllableTimeMachine<T> {

	private var erasFlow: MutableStateFlow<T>? = null

	override fun flowEras(): StateFlow<T> {
		if (erasFlow == null)
			erasFlow = MutableStateFlow(currentEra)
		return erasFlow!!
	}

	override var currentEra: T = initialEra
		private set

	override suspend fun newDestination(era: T) {
		currentEra = era
		erasFlow?.emit(currentEra)
	}
}

interface ControllableTimeMachine<T : TimeMachine.Eras> : TimeMachine<T> {
	suspend fun newDestination(era: T)
}

interface TimeMachine<T : TimeMachine.Eras> {
	interface Eras

	fun flowEras(): StateFlow<T>
	val currentEra: T
}

fun <T : TimeMachine.Eras> controllableTimeMachine(initialEra: T): ControllableTimeMachine<T> =
	DefaultTimeMachine(initialEra)

fun <T : TimeMachine.Eras> ControllableTimeMachine<T>.asTimeMachine(): TimeMachine<T> = this