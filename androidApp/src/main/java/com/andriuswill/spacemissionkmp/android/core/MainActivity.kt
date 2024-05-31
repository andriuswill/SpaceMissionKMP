package com.andriuswill.spacemissionkmp.android.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.andriuswill.spacemissionkmp.android.presenter.launchdetails.LaunchDetailsScreen
import com.andriuswill.spacemissionkmp.android.presenter.launches.LaunchesScreen
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = LaunchesScreenData//custom type
                    ) {
                        composable<LaunchesScreenData> {
                            LaunchesScreen(
                                navigateToDetails = { launchId ->
                                    navController.navigate(LaunchDetailsScreenData(launchId))
                                }
                            )
                        }
                        composable<LaunchDetailsScreenData> {
                            val args = it.toRoute<LaunchDetailsScreenData>()
                            LaunchDetailsScreen(args.launchId)
                        }
                    }
                }
            }
        }
    }
}


@Serializable
object LaunchesScreenData

@Serializable
data class LaunchDetailsScreenData(val launchId: String)