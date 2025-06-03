package com.andriuswill.spacemissionkmp.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.andriuswill.spacemissionkmp.android.presenter.launchdetails.LaunchDetailsScreen
import com.andriuswill.spacemissionkmp.android.presenter.launches.LaunchesScreen


@Composable
fun MainNavHost(){
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
