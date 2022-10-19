package com.feylabs.core.util

sealed class AppResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?=null) : AppResult<T>(data)
    class Loading<T>(data: T? = null) : AppResult<T>(data)
    class Error<T>(message: String, data: T? = null) : AppResult<T>(data, message)
}
