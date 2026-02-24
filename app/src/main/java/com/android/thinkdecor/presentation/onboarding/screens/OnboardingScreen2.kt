package com.android.thinkdecor.presentation.onboarding.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.thinkdecor.R
import com.android.thinkdecor.presentation.onboarding.components.OnboardingLayout

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun OnboardingScreen2(
    onContinue: () -> Unit = {}
) {
    OnboardingLayout(
        imageResId  = R.drawable.onboarding2,
        title       = "Total warranty if the\nproduct doesn't fit",
        subtitle    = "Semper in cursus magna et eu varius\nnunc adipiscing. Elementum justo,\nlaoreet id sem.",
        currentPage = 1,
        totalPages  = 3,
        ctaLabel    = "Continue",
        onCtaClick  = onContinue
    )
}