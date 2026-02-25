package com.android.thinkdecor.data.remote

/**
 * Sealed class representing the result of an API call.
 * Use this for consistent error handling across all API calls.
 */
sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error(val message: String, val code: Int? = null) : ApiResponse<Nothing>()
    data class Failure(val throwable: Throwable) : ApiResponse<Nothing>()
}
