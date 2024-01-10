package com.andriuswill.spacemissionkmp.android.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

abstract class BaseViewModel<S : State, in A : Action> : ViewModel() {
    abstract val state: Flow<S>
    abstract fun updateState(newState: (S) -> S)
}