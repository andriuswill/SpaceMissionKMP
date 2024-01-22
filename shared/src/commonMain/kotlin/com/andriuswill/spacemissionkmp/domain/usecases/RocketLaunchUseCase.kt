package com.andriuswill.spacemissionkmp.domain.usecases

import com.andriuswill.spacemissionkmp.base.BaseResult
import com.andriuswill.spacemissionkmp.data.remote.repository.LaunchesRepository
import com.andriuswill.spacemissionkmp.domain.entities.MainLaunches
import com.andriuswill.spacemissionkmp.domain.mappers.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LaunchesUseCase(
    private val launchesRepository: LaunchesRepository
) {
    suspend fun getMainLaunches(): Flow<BaseResult<MainLaunches>> {
        return flow {
             try {
                 val nextLaunchResponse = launchesRepository.getNextLaunch()
                 val lastLaunchResponse = launchesRepository.getLastLaunch()
                 emit(
                     BaseResult.Success(
                         MainLaunches(
                             nextLaunch = nextLaunchResponse.toModel(),
                             lastLaunch = lastLaunchResponse.toModel()
                         )
                     )
                 )
            } catch (e: Exception) {
                 emit(BaseResult.Error(e.message ?: "Some error"))
            }

        }
    }
}