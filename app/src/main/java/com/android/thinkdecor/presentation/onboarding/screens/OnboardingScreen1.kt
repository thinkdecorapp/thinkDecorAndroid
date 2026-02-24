package com.android.thinkdecor.presentation.onboarding.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.thinkdecor.R
import com.android.thinkdecor.presentation.onboarding.components.OnboardingLayout

@Composable
@Preview(showBackground = true)
fun OnboardingScreen1(
    onContinue: () -> Unit = {}
) {
    OnboardingLayout(
        imageResId = R.drawable.onboarding1,
        title = "Goods with guaranteed\nquality",
        subtitle = "Semper in cursus magna et eu varius\nnunc adipiscing. Elementum justo,\nlaoreet id sem.",
        currentPage = 0,
        totalPages = 3,
        ctaLabel = "Continue",
        onCtaClick = onContinue
    )
}