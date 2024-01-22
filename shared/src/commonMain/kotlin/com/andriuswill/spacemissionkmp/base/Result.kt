package com.andriuswill.spacemissionkmp.base

sealed class BaseResult<T : Any> {
    class Success<T: Any>(val data: T) : BaseResult<T>()
    class Error<T: Any>(val message: String) : BaseResult<T>()
}