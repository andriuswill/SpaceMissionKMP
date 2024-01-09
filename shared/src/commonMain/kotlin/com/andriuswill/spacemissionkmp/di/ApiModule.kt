package com.andriuswill.spacemissionkmp.di

import com.andriuswill.spacemissionkmp.data.LaunchesApi
import com.andriuswill.spacemissionkmp.data.remote.repository.LaunchesRepository
import com.andriuswill.spacemissionkmp.data.remote.repository.LaunchesRepositoryImpl
import com.andriuswill.spacemissionkmp.domain.usecases.LaunchesUseCase
import org.koin.dsl.module

val apiModule = module {
    //singleOf(::LaunchesApi)
    //singleOf(::LaunchesRepositoryImpl) { bind<LaunchesRepository>() }
    //singleOf(::LaunchesUseCase)
    single { LaunchesApi() }
    single<LaunchesRepository> { LaunchesRepositoryImpl(get()) }
    single { LaunchesUseCase(get()) }

}

