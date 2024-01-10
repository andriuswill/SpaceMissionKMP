package com.andriuswill.spacemissionkmp.data.remote.repository

import com.andriuswill.spacemissionkmp.data.remote.model.LaunchDto

interface LaunchesRepository {
    suspend fun getLaunches(): List<LaunchDto>
    suspend fun getNextLaunch(): LaunchDto
    suspend fun getLastLaunch(): LaunchDto
}