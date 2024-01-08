package com.andriuswill.spacemissionkmp.domain.usecases

import com.andriuswill.spacemissionkmp.data.remote.model.LaunchDto
import com.andriuswill.spacemissionkmp.data.remote.repository.LaunchesRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LaunchesUseCase {

    private val launchesRepository = LaunchesRepositoryImpl()

    suspend fun getLaunches(): Flow<Result<List<LaunchDto>>> {
        return flow {
             try {
                val response = launchesRepository.fetchLaunches()
                 emit(Result.success(response))
            } catch (e: Exception) {
                 emit(Result.failure(e))
            }
        }
    }
}