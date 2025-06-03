package com.andriuswill.spacemissionkmp.android.navigation

import kotlinx.serialization.Serializable


@Serializable
object LaunchesScreenData

@Serializable
data class LaunchDetailsScreenData(val launchId: String)
