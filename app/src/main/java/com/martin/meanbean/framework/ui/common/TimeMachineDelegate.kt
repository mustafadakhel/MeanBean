package com.martin.meanbean.framework.ui.common

import com.martin.meanbean.framework.utils.ControllableTimeMachine
import com.martin.meanbean.framework.utils.TimeMachine
import com.martin.meanbean.framework.utils.asTimeMachine
import com.martin.meanbean.framework.utils.controllableTimeMachine

interface TimeMachineDelegate<T : TimeMachine.Eras> {
	val timeMachine: TimeMachine<T>
	suspend fun TimeMachine<T>.newDestination(era: T)
}

class DefaultTimeMachineDelegate<T : TimeMachine.Eras>(initialEra: T) :
	TimeMachineDelegate<T> {

	private val _timeMachine: ControllableTimeMachine<T> = controllableTimeMachine(initialEra)
	override val timeMachine: TimeMachine<T> = _timeMachine.asTimeMachine()
	override suspend fun TimeMachine<T>.newDestination(era: T) {
		_timeMachine.newDestination(era)
	}
}