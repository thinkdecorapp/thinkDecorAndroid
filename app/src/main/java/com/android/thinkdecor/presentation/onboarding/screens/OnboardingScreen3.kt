package com.android.thinkdecor.presentation.onboarding.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.thinkdecor.R
import com.android.thinkdecor.presentation.onboarding.components.OnboardingLayout

@Composable
fun OnboardingScreen3(
    onContinue: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    OnboardingLayout(
        imageResId  = R.drawable.onboarding3,
        title       = "Let's fulfill your housing\nneeds in Dream Decor.",
        subtitle    = "Lorem Ipsum is simply dummy text of the printing\nand typesetting industry.",
        currentPage = 2,
        totalPages  = 3,
        ctaLabel    = "Get Started",
        onCtaClick  = onContinue,
        secondaryPrefix = "Don't have an account?",
        secondaryLabel = "Sign up",
        onSecondaryClick = onSignUpClick
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingScreen3Preview() {
    OnboardingScreen3()
}