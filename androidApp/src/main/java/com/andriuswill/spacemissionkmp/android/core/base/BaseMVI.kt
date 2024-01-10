package com.andriuswill.spacemissionkmp.android.core.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class Reducer<S : State, A : Action>(initialValue: S) {

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialValue)

    val state: StateFlow<S>
        get() = _state

    fun sendAction(action: A) {
        reduce(_state.value, action)
    }

    fun setState(newState: S) {
        _state.tryEmit(newState)
    }

    abstract fun reduce(oldState: S, action: A)
}

interface State

interface Action