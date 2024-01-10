package com.andriuswill.spacemissionkmp.android.presenter.launches

import androidx.lifecycle.viewModelScope
import com.andriuswill.spacemissionkmp.android.core.base.BaseViewModel
import com.andriuswill.spacemissionkmp.android.core.base.Reducer
import com.andriuswill.spacemissionkmp.domain.usecases.LaunchesUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    launchesUseCase: LaunchesUseCase
): BaseViewModel<LaunchesState, LaunchesAction>() {

    override val state: StateFlow<LaunchesState>
        get() = reducer.state

    override fun updateState(newState: (LaunchesState) -> LaunchesState) {
        reducer.setState(newState.invoke(reducer.state.value))
    }

    private val reducer = LaunchesReducer(LaunchesState.initial())

    inner class LaunchesReducer(
        initialValue: LaunchesState
    ) : Reducer<LaunchesState, LaunchesAction>(initialValue) {
        override fun reduce(oldState: LaunchesState, action: LaunchesAction) {
            when(action){
                is LaunchesAction.LoadLaunches -> Unit
                is LaunchesAction.NavigateToPastLaunches -> Unit
                is LaunchesAction.NavigateToUpcomingLaunches -> Unit
            }
        }
    }

    init {
        viewModelScope.launch {
            launchesUseCase.getNextLaunch().collect { result ->
                if(result.isSuccess){
                    updateState { currentState ->
                        currentState.copy(
                            nextLaunch = result.getOrNull()
                        )
                    }
                }
            }

            launchesUseCase.getLastLaunch().collect { result ->
                if(result.isSuccess){
                    updateState { currentState ->
                        currentState.copy(
                            lastLaunch = result.getOrNull()
                        )
                    }
                }
            }
        }
    }
}