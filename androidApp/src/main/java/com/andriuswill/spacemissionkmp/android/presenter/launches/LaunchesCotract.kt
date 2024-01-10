package com.andriuswill.spacemissionkmp.android.presenter.launches

import com.andriuswill.spacemissionkmp.android.core.base.Action
import com.andriuswill.spacemissionkmp.android.core.base.State
import com.andriuswill.spacemissionkmp.domain.entities.Launch

data class LaunchesState(
    val nextLaunch: Launch?,
    val lastLaunch: Launch?
) : State {
    companion object {
        fun initial() = LaunchesState(
            nextLaunch = null,
            lastLaunch = null
        )
    }
}

sealed class LaunchesAction : Action {
    data object LoadLaunches : LaunchesAction()
    data object NavigateToLaunch : LaunchesAction()
}
