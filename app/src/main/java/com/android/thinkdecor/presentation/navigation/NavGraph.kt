package com.android.thinkdecor.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import com.android.thinkdecor.presentation.auth.screens.EnterOTPScreen
import com.android.thinkdecor.presentation.auth.screens.ForgotPasswordScreen
import com.android.thinkdecor.presentation.auth.screens.ChooseInterestsScreen
import com.android.thinkdecor.presentation.auth.screens.CreateNewPasswordScreen
import com.android.thinkdecor.presentation.auth.screens.PopUpScreen
import com.android.thinkdecor.presentation.auth.screens.SignInScreen
import com.android.thinkdecor.presentation.auth.screens.SignUpScreen
import com.android.thinkdecor.presentation.auth.screens.SuccessPopup

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Routes.SignIn.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        // Sign In Screen
        composable(route = Routes.SignIn.route) {
            SignInScreen(
                onSignUpClick = {
                    navController.navigate(Routes.SignUp.route)
                },
                onSignInClick = {
                    navController.navigate(Routes.ChooseInterests.route) {
                        popUpTo(Routes.SignIn.route) { inclusive = true }
                    }
                },
                onForgotPasswordClick = {
                    navController.navigate(Routes.ForgotPassword.route)
                },
                onBackClick = {
                    // go back to onboarding to let user choose if he's a business or customer
                }
            )
        }

        // Sign Up Screen
        composable(route = Routes.SignUp.route) {
            SignUpScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onSignUpClick = { email ->
                    navController.navigate(Routes.EnterOTP.createRoute(email))
                }
            )
        }

        // Enter OTP Screen
        composable(
            route = Routes.EnterOTP.route,
            arguments = listOf(
                navArgument(Routes.ARG_EMAIL) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString(Routes.ARG_EMAIL) ?: ""

            EnterOTPScreen(
                email = email,
                onContinueClick = {
                    navController.navigate(
                        Routes.Success.createRoute(Routes.SUCCESS_TYPE_OTP_VERIFIED)
                    ) {
                        popUpTo(Routes.SignUp.route) { inclusive = true }
                    }
                },
                onResendClick = {
                    // resend OTP logic
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Forgot Password Screen
        composable(route = Routes.ForgotPassword.route) {
            ForgotPasswordScreen(
                onNextClick = { email ->
                    // Navigate to create new password with email
                    navController.navigate(Routes.CreateNewPassword.createRoute(email))
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Create New Password Screen
        composable(
            route = Routes.CreateNewPassword.route,
            arguments = listOf(
                navArgument(Routes.ARG_EMAIL) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString(Routes.ARG_EMAIL) ?: ""

            CreateNewPasswordScreen(
                email = email,
                onNextClick = {
                    navController.navigate(
                        Routes.Success.createRoute(Routes.SUCCESS_TYPE_PASSWORD_RESET)
                    ) {
                        popUpTo(Routes.ForgotPassword.route) { inclusive = true }
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Success Screen
        composable(
            route = Routes.Success.route,
            arguments = listOf(
                navArgument(Routes.ARG_TYPE) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val type = backStackEntry.arguments?.getString(Routes.ARG_TYPE) ?: ""

            SuccessPopup(
                message = type,
                onContinueClick = {
                    when (type) {
                        Routes.SUCCESS_TYPE_PASSWORD_RESET -> {
                            // Navigate to sign in
                            navController.navigate(Routes.SignIn.route) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                        Routes.SUCCESS_TYPE_OTP_VERIFIED -> {
                            // Navigate to interests
                            navController.navigate(Routes.ChooseInterests.route) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                        else -> {
                           // Move to HOME screen
                        }
                    }
                }
            )
        }

        // Choose Interests Screen
        composable(route = Routes.ChooseInterests.route) {
            ChooseInterestsScreen(
                onFinishClick = {
                    // Navigate to home
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        dialog(route = "pop_up_otp") {
            var showDialog by remember { mutableStateOf(true) }

            if (showDialog) {
                PopUpScreen(
                    onDismiss = {
                        showDialog = false
                        navController.popBackStack()
                    },
                    onVerifyClick = {
                        showDialog = false
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
