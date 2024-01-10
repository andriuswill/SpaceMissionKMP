package com.andriuswill.spacemissionkmp.android.presenter.launches

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.compose.koinInject

@Composable
fun LaunchesScreen() {
    val mainViewModel: MainViewModel = koinInject<MainViewModel>()

    val launches = mainViewModel.state.collectAsState()
    LaunchesContent(state = launches.value)
}

@Composable
fun LaunchesContent(state: LaunchesState) {
    Column {
        Text(text = state.nextLaunch?.missionName.orEmpty())
        Divider()
        Text(text = state.lastLaunch?.missionName.orEmpty())
    }
}