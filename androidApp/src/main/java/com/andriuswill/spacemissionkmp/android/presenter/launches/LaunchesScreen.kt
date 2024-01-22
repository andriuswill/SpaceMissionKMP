package com.andriuswill.spacemissionkmp.android.presenter.launches

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.andriuswill.spacemissionkmp.android.core.component.ErrorComponent
import com.andriuswill.spacemissionkmp.android.core.component.LoadingComponent
import com.andriuswill.spacemissionkmp.domain.entities.MainLaunches
import org.koin.compose.koinInject

@Composable
fun LaunchesScreen() {
    val mainViewModel: MainViewModel = koinInject<MainViewModel>()

    when(
        val launchesState = mainViewModel.state.collectAsState().value
    ) {
        is LaunchesState.Initial -> {
            mainViewModel.sendEvent(LaunchesAction.LoadLaunches)
        }
        is LaunchesState.Loading -> {
            LoadingComponent()
        }
        is LaunchesState.Success -> {
            LaunchesContent(mainLaunches = launchesState.mainLaunches)
        }
        is LaunchesState.Error -> {
            ErrorComponent(message = launchesState.message)
        }
    }
}

@Composable
fun LaunchesContent(mainLaunches: MainLaunches) {
    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxSize()
    ) {
        LaunchCard(mainLaunches.nextLaunch.missionName)
        Spacer(Modifier.size(32.dp))
        LaunchCard(mainLaunches.lastLaunch.missionName)
    }
}

@Composable
fun ColumnScope.LaunchCard(launchTitle: String){
    Box(modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .weight(1F)
        .fillMaxWidth()
        .border(
            width = 4.dp,
            color = Gray,
            shape = RoundedCornerShape(16.dp)
        )
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = launchTitle,
            textAlign = TextAlign.Center,
            style = typography.displayMedium
        )
    }
}