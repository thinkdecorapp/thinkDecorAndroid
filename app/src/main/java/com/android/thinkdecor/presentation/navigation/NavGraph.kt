package com.android.thinkdecor.presentation.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.android.thinkdecor.presentation.auth.screens.ChooseInterestsScreen
import com.android.thinkdecor.presentation.auth.screens.CreateNewPasswordScreen
import com.android.thinkdecor.presentation.auth.screens.EnterOTPScreen
import com.android.thinkdecor.presentation.auth.screens.ForgotPasswordScreen
import com.android.thinkdecor.presentation.auth.screens.PopUpScreen
import com.android.thinkdecor.presentation.auth.screens.SignInScreen
import com.android.thinkdecor.presentation.auth.screens.SignUpScreen
import com.android.thinkdecor.presentation.auth.screens.SuccessPopup
import com.android.thinkdecor.presentation.cart.CartScreen
import com.android.thinkdecor.presentation.chat.mockData.FakeConversations
import com.android.thinkdecor.presentation.chat.mockData.FakeMessages.mockChatMessages
import com.android.thinkdecor.presentation.chat.screens.ChatScreen
import com.android.thinkdecor.presentation.chat.screens.ConversationsScreen
import com.android.thinkdecor.presentation.dashboard.DashboardUIDesign
import com.android.thinkdecor.presentation.onboarding.screens.OnboardingCarouselScreen
import com.android.thinkdecor.presentation.onboarding.screens.SplashScreen
import com.android.thinkdecor.presentation.screens.ExploreScreen
import com.android.thinkdecor.presentation.screens.HomeScreen
import com.android.thinkdecor.presentation.screens.ProfileScreen
import com.android.thinkdecor.presentation.screens.ScanScreen
import kotlinx.coroutines.delay

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Routes.Splash.route,
    onSignInSuccess: () -> Unit = {}
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Routes.Splash.route) {
            SplashScreen(
                onSplashComplete = {
                    LaunchedEffect(Unit) {
                        delay(1500)
                        navController.navigate(Routes.Onboarding.route) {
                            popUpTo(Routes.Splash.route) { inclusive = true }
                        }
                    }
                }
            )
        }

        composable(Routes.Onboarding.route) {
            OnboardingCarouselScreen(
                onFinished = {
                    navController.navigate(Routes.SignIn.route) {
                        popUpTo(Routes.Splash.route) { inclusive = true }
                    }
                },
                onSignUpClick = {
                    navController.navigate(Routes.SignUp.route)
                }
            )
        }

        // Sign In Screen
        composable(route = Routes.SignIn.route) {
            SignInScreen(
                onSignUpClick = {
                    navController.navigate(Routes.SignUp.route)
                },
                onSignInClick = {
                    onSignInSuccess()
                },
                onForgotPasswordClick = {
                    navController.navigate(Routes.ForgotPassword.route)
                },
                onBackClick = {
                    navController.popBackStack()
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
                },
                onSignInClick = {
                    navController.navigate(Routes.SignIn.route) {
                        popUpTo(Routes.SignUp.route) { inclusive = true }
                    }
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
                    Toast.makeText(
                        navController.context,
                        "Interests saved successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    navController.navigate(Routes.Dashboard.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onBackClick = {
                    navController.navigate(Routes.SignIn.route) {
                        popUpTo(0) { inclusive = true }
                    }
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

        composable(
            route = Routes.Dashboard.route
        ) {
            DashboardUIDesign(parentNavController = navController)
        }

        composable(route = Routes.Home.route) {
            HomeScreen(
                onCartClick = {
                    navController.navigate(Routes.Cart.route)
                }
            )
        }

        composable(Routes.Cart.route) {
            CartScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.Explore.route
        ) {
            ExploreScreen()
        }

        composable(
            route = Routes.Scan.route
        ) {
            ScanScreen()
        }

        composable(route = Routes.Conversations.route) {
            ConversationsScreen(
                items = FakeConversations.mockConversations,
                onOpenChat = { conversation ->
                    navController.navigate(
                        Routes.Chat.createRoute(conversation.id)
                    )
                }
            )
        }

        composable(
            route = Routes.Chat.route,
            arguments = listOf(
                navArgument(Routes.ARG_CHAT_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val chatId = backStackEntry.arguments?.getString(Routes.ARG_CHAT_ID) ?: ""
            val user = FakeConversations.mockConversations.find { it.id == chatId }
            ChatScreen(
                userName = user?.name ?: "Unknown",
                messages = mockChatMessages,
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.Profile.route
        ) {
            ProfileScreen()
        }

    }
}
