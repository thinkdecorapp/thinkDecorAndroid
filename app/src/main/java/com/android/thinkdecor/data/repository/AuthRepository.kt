package com.android.thinkdecor.data.repository

import com.android.thinkdecor.data.remote.ApiResponse
import com.android.thinkdecor.data.remote.AuthApi
import com.android.thinkdecor.data.remote.AuthResponse
import com.android.thinkdecor.data.remote.LoginRequest
import com.android.thinkdecor.data.remote.SignUpRequest
import com.android.thinkdecor.data.remote.safeApiCall

class AuthRepository(
    private val api: AuthApi
) {

    suspend fun signUp(
        fullName: String,
        email: String,
        password: String
    ): ApiResponse<AuthResponse> = safeApiCall {
        api.signUp(SignUpRequest(fullName = fullName, email = email, password = password))
    }

    suspend fun login(
        email: String,
        password: String
    ): ApiResponse<AuthResponse> = safeApiCall {
        api.login(LoginRequest(email = email, password = password))
    }
}
