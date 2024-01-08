package com.andriuswill.spacemissionkmp.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriuswill.spacemissionkmp.data.remote.model.LaunchDto
import com.andriuswill.spacemissionkmp.domain.usecases.LaunchesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    private val launchesUseCase: LaunchesUseCase = LaunchesUseCase()

    private val _launchesList = MutableStateFlow<List<LaunchDto>>(listOf())
    val launchesList: StateFlow<List<LaunchDto>> get() = _launchesList

    init {
        viewModelScope.launch {
            launchesUseCase.getLaunches().collect { result ->
                if(result.isSuccess){
                    _launchesList.update {
                        result.getOrDefault(listOf())
                    }
                }
            }
        }
    }
}