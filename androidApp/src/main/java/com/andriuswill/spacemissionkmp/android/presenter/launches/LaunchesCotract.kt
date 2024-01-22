package com.andriuswill.spacemissionkmp.android.presenter.launches

import com.andriuswill.spacemissionkmp.android.core.base.Action
import com.andriuswill.spacemissionkmp.android.core.base.State
import com.andriuswill.spacemissionkmp.domain.entities.Launch
import com.andriuswill.spacemissionkmp.domain.entities.MainLaunches

sealed class LaunchesState: State {
    data object Initial: LaunchesState()
    data object Loading: LaunchesState()
    data class Success(val mainLaunches: MainLaunches): LaunchesState()
    data class Error(val message: String): LaunchesState()
}

sealed class LaunchesAction : Action {
    data object LoadLaunches : LaunchesAction()
    data object NavigateToLaunch : LaunchesAction()
}
