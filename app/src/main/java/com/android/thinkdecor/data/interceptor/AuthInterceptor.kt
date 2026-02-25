package com.android.thinkdecor.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = getToken() // Replace with DataStore/Preferences
        val request = chain.request().newBuilder().apply {
            if (token.isNotBlank()) {
                addHeader("Authorization", "Bearer $token")
            }
        }.build()
        return chain.proceed(request)
    }

    private fun getToken(): String = "" // TODO: Get from DataStore
}