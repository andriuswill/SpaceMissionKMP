package com.andriuswill.spacemissionkmp.android.di

import com.andriuswill.spacemissionkmp.android.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val launchModule = module {
    viewModel { MainViewModel(get()) }
}
