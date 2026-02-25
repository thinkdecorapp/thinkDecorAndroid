package com.android.thinkdecor.presentation.onboarding.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.android.thinkdecor.presentation.onboarding.components.OnboardingPage
import com.android.thinkdecor.R
import com.android.thinkdecor.presentation.onboarding.components.OnboardingLayout
import com.android.thinkdecor.presentation.onboarding.components.OnboardingTheme
import kotlinx.coroutines.delay

@Composable
fun OnboardingCarouselScreen(
    onFinished: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val onboardingPages = listOf(
        OnboardingPage(
            R.drawable.onboarding1,
            "Goods with guaranteed\nquality",
            "Semper in cursus magna et eu varius\nnunc adipiscing. Elementum justo,\nlaoreet id sem."
        ),
        OnboardingPage(
            R.drawable.onboarding2,
            "Total warranty if the\nproduct doesn't fit",
            "Semper in cursus magna et eu varius\nnunc adipiscing. Elementum justo,\nlaoreet id sem."
        ),
        OnboardingPage(
            R.drawable.onboarding3,
            "Let's fulfill your housing\nneeds in Dream Decor.",
            "Lorem Ipsum is simply dummy text of the printing\nand typesetting industry."
        )
    )

    var page by remember { mutableIntStateOf(0) }
    val pageCount = onboardingPages.size

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            page = (page + 1) % pageCount
        }
    }

    val current = onboardingPages[page]

    OnboardingLayout(
        imageResId = current.imageRes,
        title = current.title,
        subtitle = current.subtitle,
        currentPage = page,
        totalPages = pageCount,
        ctaLabel = if (page == pageCount - 1) "Get Started" else "Continue",
        onCtaClick = {
            if (page == pageCount - 1) {
                onFinished()
            } else {
                page++
            }
        },
        secondaryPrefix = if (page == pageCount - 1) "Don't have an account?" else null,
        secondaryLabel = if (page == pageCount - 1) "Sign up" else null,
        onSecondaryClick = if (page == pageCount - 1) onSignUpClick else null
    )
}

@Composable
fun OnboardingDots(
    currentPage: Int,
    totalPages: Int,
    dotHeight: Dp = 6.dp,
    inactiveWidth: Dp = 6.dp,
    activeWidth: Dp = 18.dp,
    spacing: Dp = 8.dp
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalPages) { index ->
            val isSelected = index == currentPage

            val width by animateDpAsState(
                targetValue = if (isSelected) activeWidth else inactiveWidth,
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
                label = "dot_width"
            )

            val color by animateColorAsState(
                targetValue = if (isSelected)
                    OnboardingTheme.TealButton
                else
                    Color.White.copy(alpha = 0.4f),
                animationSpec = tween(300),
                label = "dot_color"
            )

            Box(
                modifier = Modifier
                    .height(dotHeight)
                    .width(width)
                    .clip(RoundedCornerShape(dotHeight))
                    .background(color)
            )
        }
    }
}