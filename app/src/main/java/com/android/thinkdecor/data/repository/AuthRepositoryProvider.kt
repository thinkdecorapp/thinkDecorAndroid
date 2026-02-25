package com.android.thinkdecor.data.repository

import com.android.thinkdecor.data.remote.NetworkModule

object AuthRepositoryProvider {

    val authRepository: AuthRepository by lazy {
        AuthRepository(NetworkModule.authApi)
    }
}
