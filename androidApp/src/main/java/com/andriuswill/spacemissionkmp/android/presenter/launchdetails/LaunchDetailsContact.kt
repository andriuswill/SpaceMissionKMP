package com.andriuswill.spacemissionkmp.android.presenter.launchdetails

import com.andriuswill.spacemissionkmp.android.core.base.Action
import com.andriuswill.spacemissionkmp.android.core.base.State

sealed class LaunchDetailsState: State {
    data object Initial: LaunchDetailsState()
    data object Loading: LaunchDetailsState()
    data object Success: LaunchDetailsState()
    data class Error(val message: String): LaunchDetailsState()
}

sealed class LaunchDetailsAction : Action {
    data object LoadLaunchDetails : LaunchDetailsAction()
}