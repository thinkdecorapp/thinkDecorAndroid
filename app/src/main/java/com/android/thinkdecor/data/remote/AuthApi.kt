package com.android.thinkdecor.data.remote

import com.android.thinkdecor.data.remote.dto.AuthResponse
import com.android.thinkdecor.data.remote.dto.LoginRequest
import com.android.thinkdecor.data.remote.dto.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): AuthResponse

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse
}
