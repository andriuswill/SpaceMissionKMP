package com.andriuswill.spacemissionkmp.android.presenter.launchdetails

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.andriuswill.spacemissionkmp.android.core.component.ErrorComponent
import com.andriuswill.spacemissionkmp.android.core.component.LoadingComponent
import org.koin.compose.koinInject

@Composable
fun LaunchDetailsScreen(
    launchId: String
) {
    val launchDetailsViewModel = koinInject<LaunchDetailsViewModel>()

    when(
        val launchDetailState = launchDetailsViewModel.state.collectAsState().value
    ) {
        is LaunchDetailsState.Initial -> {
            launchDetailsViewModel.sendEvent(LaunchDetailsAction.LoadLaunchDetails)
        }
        is LaunchDetailsState.Loading -> {
            LoadingComponent()
        }
        is LaunchDetailsState.Success -> {
            LaunchDetailsContent(launchId)
        }
        is LaunchDetailsState.Error -> {
            ErrorComponent(message = launchDetailState.message)
        }
    }
    LaunchDetailsContent(launchId)
}

@Composable
fun LaunchDetailsContent(launchId: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .border(
            width = 4.dp,
            color = Color.Gray,
            shape = RoundedCornerShape(16.dp)
        )
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = launchId,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayMedium
        )
    }
}