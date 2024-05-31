package com.andriuswill.spacemissionkmp.android.di

import com.andriuswill.spacemissionkmp.android.presenter.launchdetails.LaunchDetailsViewModel
import com.andriuswill.spacemissionkmp.android.presenter.launches.LaunchesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val launchModule = module {
    viewModel { LaunchesViewModel(get()) }
    viewModel { LaunchDetailsViewModel(get()) }
}
