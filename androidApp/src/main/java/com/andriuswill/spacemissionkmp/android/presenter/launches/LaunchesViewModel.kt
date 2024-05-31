package com.andriuswill.spacemissionkmp.android.presenter.launches

import androidx.lifecycle.viewModelScope
import com.andriuswill.spacemissionkmp.android.core.base.BaseViewModel
import com.andriuswill.spacemissionkmp.android.core.base.Reducer
import com.andriuswill.spacemissionkmp.base.BaseResult
import com.andriuswill.spacemissionkmp.domain.usecases.LaunchesUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LaunchesViewModel(
    private val launchesUseCase: LaunchesUseCase
): BaseViewModel<LaunchesState, LaunchesAction>() {

    override val state: StateFlow<LaunchesState>
        get() = reducer.state

    override fun updateState(newState: (LaunchesState) -> LaunchesState) {
        reducer.setState(newState.invoke(reducer.state.value))
    }

    private val reducer = LaunchesReducer(LaunchesState.Initial)

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
            updateState {
                LaunchesState.Loading
            }
            launchesUseCase.getMainLaunches().collect { result ->
                when(result){
                    is BaseResult.Error -> {
                        updateState {
                            LaunchesState.Error(result.message)
                        }
                    }
                    is BaseResult.Success -> {
                        updateState {
                            LaunchesState.Success(result.data)
                        }
                    }
                }
            }
        }
    }
}