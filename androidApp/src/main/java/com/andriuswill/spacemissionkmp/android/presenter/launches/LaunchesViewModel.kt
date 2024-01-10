package com.andriuswill.spacemissionkmp.android.presenter.launches

import androidx.lifecycle.viewModelScope
import com.andriuswill.spacemissionkmp.android.core.base.BaseViewModel
import com.andriuswill.spacemissionkmp.android.core.base.Reducer
import com.andriuswill.spacemissionkmp.domain.usecases.LaunchesUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val launchesUseCase: LaunchesUseCase
): BaseViewModel<LaunchesState, LaunchesAction>() {

    override val state: StateFlow<LaunchesState>
        get() = reducer.state

    override fun updateState(newState: (LaunchesState) -> LaunchesState) {
        reducer.setState(newState.invoke(reducer.state.value))
    }

    private val reducer = LaunchesReducer(LaunchesState.initial())

    fun sendEvent(action: LaunchesAction) {
        reducer.sendAction(action)
    }

    inner class LaunchesReducer(
        initialValue: LaunchesState
    ) : Reducer<LaunchesState, LaunchesAction>(initialValue) {
        override fun reduce(oldState: LaunchesState, action: LaunchesAction) {
            when(action){
                is LaunchesAction.LoadLaunches -> loadLaunches()
                is LaunchesAction.NavigateToLaunch -> Unit
            }
        }
    }

    fun loadLaunches(){
        viewModelScope.launch {
            launchesUseCase.getMainLaunches().collect { result ->
                if(result.isSuccess){
                    updateState { currentState ->
                        currentState.copy(
                            nextLaunch = result.getOrNull()?.nextLaunch,
                            lastLaunch = result.getOrNull()?.lastLaunch
                        )
                    }
                }
            }
        }
    }
}