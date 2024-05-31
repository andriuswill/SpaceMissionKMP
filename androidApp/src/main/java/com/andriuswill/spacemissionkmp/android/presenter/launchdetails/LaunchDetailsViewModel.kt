package com.andriuswill.spacemissionkmp.android.presenter.launchdetails

import androidx.lifecycle.viewModelScope
import com.andriuswill.spacemissionkmp.android.core.base.BaseViewModel
import com.andriuswill.spacemissionkmp.android.core.base.Reducer
import com.andriuswill.spacemissionkmp.domain.usecases.LaunchesUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LaunchDetailsViewModel(
    private val launchesUseCase: LaunchesUseCase
) : BaseViewModel<LaunchDetailsState, LaunchDetailsAction>() {

    override val state: StateFlow<LaunchDetailsState>
        get() = reducer.state

    override fun updateState(newState: (LaunchDetailsState) -> LaunchDetailsState) {
        reducer.setState(newState.invoke(reducer.state.value))
    }

    private val reducer = LaunchDetailsReducer(LaunchDetailsState.Initial)

    fun sendEvent(action: LaunchDetailsAction) {
        reducer.sendAction(action)
    }

    inner class LaunchDetailsReducer(
        initialValue: LaunchDetailsState
    ) : Reducer<LaunchDetailsState, LaunchDetailsAction>(initialValue) {
        override fun reduce(oldState: LaunchDetailsState, action: LaunchDetailsAction) {
            when(action){
                is LaunchDetailsAction.LoadLaunchDetails -> Unit
            }
        }
    }

    fun loadLaunchDetails(){
        viewModelScope.launch {

        }
    }
}