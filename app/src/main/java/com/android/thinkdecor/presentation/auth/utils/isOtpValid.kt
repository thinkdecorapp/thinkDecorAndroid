package com.android.thinkdecor.presentation.auth.utils

fun isOtpValid(otp: String): Boolean {
    return otp.length == 4 && otp.all { it.isDigit() }
}
