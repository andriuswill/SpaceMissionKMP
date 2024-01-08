package com.andriuswill.spacemissionkmp.domain

sealed class UIState<out R> {
    data object Init : UIState<Nothing>()
    data object Loading : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Error(val exception: Exception) : UIState<Nothing>()
}