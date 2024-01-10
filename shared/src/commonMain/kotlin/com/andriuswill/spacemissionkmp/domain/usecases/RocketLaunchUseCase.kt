package com.andriuswill.spacemissionkmp.domain.usecases

import com.andriuswill.spacemissionkmp.data.remote.repository.LaunchesRepository
import com.andriuswill.spacemissionkmp.domain.entities.Launch
import com.andriuswill.spacemissionkmp.domain.mappers.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LaunchesUseCase(
    private val launchesRepository: LaunchesRepository
) {
    suspend fun getLaunches(): Flow<Result<List<Launch>>> {
        return flow {
             try {
                val response = launchesRepository.getLaunches()
                 emit(Result.success(response.map { it.toModel() }))
            } catch (e: Exception) {
                 emit(Result.failure(e))
            }
        }
    }

    suspend fun getNextLaunch(): Flow<Result<Launch>> {
        return flow {
            try {
                val response = launchesRepository.getNextLaunch()
                emit(Result.success(response.toModel()))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }

    suspend fun getLastLaunch(): Flow<Result<Launch>> {
        return flow {
            try {
                val response = launchesRepository.getLastLaunch()
                emit(Result.success(response.toModel()))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }
}