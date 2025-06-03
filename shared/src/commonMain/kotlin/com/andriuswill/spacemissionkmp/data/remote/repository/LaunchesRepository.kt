package com.andriuswill.spacemissionkmp.data.remote.repository

import com.andriuswill.spacemissionkmp.data.remote.model.LaunchDetailsDto
import com.andriuswill.spacemissionkmp.data.remote.model.LaunchDto

interface LaunchesRepository {
    suspend fun getNextLaunch(): LaunchDto
    suspend fun getLastLaunch(): LaunchDto
    suspend fun getPastLaunches(): List<LaunchDto>
    suspend fun getUpcomingLaunches(): List<LaunchDto>
    suspend fun getLaunchDetail(launchId: String): LaunchDetailsDto
}