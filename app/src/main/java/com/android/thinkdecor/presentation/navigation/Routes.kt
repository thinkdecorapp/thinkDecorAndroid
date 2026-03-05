package com.android.thinkdecor.presentation.navigation

sealed class Routes(val route: String) {

    // Onboarding Routes
    object Splash : Routes("splash")
    object Onboarding : Routes("onboarding")

    // Authentication Routes
    object SignIn : Routes("sign_in")
    object SignUp : Routes("sign_up")
    object EnterOTP : Routes("enter_otp/{email}") {
        fun createRoute(email: String) = "enter_otp/$email"
    }
    object ForgotPassword : Routes("forgot_password")
    object CreateNewPassword : Routes("create_new_password/{email}") {
        fun createRoute(email: String) = "create_new_password/$email"
    }
    object Success : Routes("success/{type}") {
        fun createRoute(type: String) = "success/$type"
    }
    object ChooseInterests : Routes("choose_interests")

    // Dashboard Routes

    object Dashboard : Routes("dashboard")
    object Home : Routes("home")

    object Cart : Routes("cart")
    object Explore : Routes("explore")
    object Scan : Routes("scan")
    object Conversations : Routes("conversations")
    object Chat : Routes("chat/{id}") {
        fun createRoute(id: String) = "chat/$id"
    }

    object Profile : Routes("profile")

    companion object {
        // Route constants for navigation arguments
        const val ARG_EMAIL = "email"
        const val ARG_TYPE = "type"
        const val ARG_CHAT_ID = "id"

        // Success types
        const val SUCCESS_TYPE_PASSWORD_RESET = "password_reset"
        const val SUCCESS_TYPE_ACCOUNT_CREATED = "account_created"
        const val SUCCESS_TYPE_OTP_VERIFIED = "otp_verified"
    }
}
