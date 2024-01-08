package com.andriuswill.spacemissionkmp.data.remote.repository

import com.andriuswill.spacemissionkmp.data.remote.model.LaunchDto

interface LaunchesRepository {
    suspend fun fetchLaunches(): List<LaunchDto>
}