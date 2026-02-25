package com.android.thinkdecor.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Wraps an API call in a try-catch and returns [ApiResponse].
 * Use this for all API calls to ensure consistent error handling.
 *
 * Usage:
 * ```
 * val result = safeApiCall {
 *     api.getProducts()
 * }
 * when (result) {
 *     is ApiResponse.Success -> // handle data
 *     is ApiResponse.Error -> // handle error message
 *     is ApiResponse.Failure -> // handle exception
 * }
 * ```
 */
suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ApiResponse<T> = withContext(Dispatchers.IO) {
    try {
        val response = apiCall()
        ApiResponse.Success(response)
    } catch (e: HttpException) {
        ApiResponse.Error(
            message = e.response()?.errorBody()?.string()?.let { parseErrorMessage(it) }
                ?: e.message()
                ?: "Unknown error",
            code = e.code()
        )
    } catch (e: UnknownHostException) {
        ApiResponse.Error(message = "No internet connection", code = null)
    } catch (e: SocketTimeoutException) {
        ApiResponse.Error(message = "Connection timed out", code = null)
    } catch (e: IOException) {
        ApiResponse.Error(message = "Network error: ${e.message}", code = null)
    } catch (e: Exception) {
        ApiResponse.Failure(e)
    }
}

/**
 * Parses error message from JSON error body.
 * Override or extend for your API's error format.
 */
private fun parseErrorMessage(errorBody: String): String {
    return try {
        // Common patterns: "message", "error", "detail"
        when {
            errorBody.contains("\"message\"") -> extractJsonString(errorBody, "message")
            errorBody.contains("\"error\"") -> extractJsonString(errorBody, "error")
            errorBody.contains("\"detail\"") -> extractJsonString(errorBody, "detail")
            errorBody.contains("\"msg\"") -> extractJsonString(errorBody, "msg")
            else -> errorBody.take(200) // Fallback: first 200 chars
        }
    } catch (e: Exception) {
        errorBody.take(200)
    }
}

private fun extractJsonString(json: String, key: String): String {
    val regex = """"$key"\s*:\s*"([^"]*)"""".toRegex()
    return regex.find(json)?.groupValues?.get(1) ?: json.take(200)
}
