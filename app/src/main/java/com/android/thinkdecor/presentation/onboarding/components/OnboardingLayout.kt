package com.android.thinkdecor.presentation.onboarding.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.R

object OnboardingTheme {
    val BackgroundDark = Color(0xFF141414)
    val TealButton = Color(0xFF00695C)
    val White = Color(0xFFFFFFFF)
    val SubtitleGray = Color(0xFFAAAAAA)
    val DotActive = Color(0xFFFFFFFF)
    val DotInactive = Color(0x55FFFFFF)
}


@Composable
fun OnboardingLayout(
    imageResId: Int,
    imageHeightFrac: Float = 0.62f,
    title: String,
    subtitle: String,
    currentPage: Int,
    totalPages: Int = 3,
    ctaLabel: String = "Continue",
    onCtaClick: () -> Unit = {},
    secondaryPrefix: String? = null,
    secondaryLabel: String? = null,
    onSecondaryClick: (() -> Unit)? = null,
    topContent: @Composable (BoxScope.() -> Unit)? = null
) {
    // ── Entrance animations ──────────────────────────────────────────────────
    val imageAlpha   = remember { Animatable(0f) }
    val imageOffsetY = remember { Animatable(-24f) }
    val textAlpha    = remember { Animatable(0f) }
    val textOffsetY  = remember { Animatable(20f) }
    val buttonScale  = remember { Animatable(0.9f) }
    val buttonAlpha  = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        imageAlpha.animateTo(1f, tween(550, easing = EaseOut))
        imageOffsetY.animateTo(0f, tween(550, easing = EaseOutCubic))

        textAlpha.animateTo(1f, tween(450, delayMillis = 150, easing = EaseOut))
        textOffsetY.animateTo(0f, tween(450, delayMillis = 150, easing = EaseOutCubic))

        buttonAlpha.animateTo(1f, tween(350, delayMillis = 300, easing = EaseOut))
        buttonScale.animateTo(
            targetValue   = 1f,
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OnboardingTheme.BackgroundDark)
    ) {
        Box(
            modifier = Modifier
                .alpha(imageAlpha.value)
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Bottom fade-out gradient
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colorStops = arrayOf(
                                0.0f to Color.Transparent,
                                0.50f to OnboardingTheme.BackgroundDark.copy(alpha = 0.3f),
                                1.0f to OnboardingTheme.BackgroundDark
                            )
                        )
                    )
            )
        }

        topContent?.let {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                content = it
            )
        }

        // ── Bottom Content ────────────────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 28.dp)
                .padding(bottom = 48.dp)
                .alpha(textAlpha.value)
                .offset(y = textOffsetY.value.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Title
            Text(
                text          = title,
                color         = OnboardingTheme.White,
                fontSize      = 26.sp,
                fontWeight    = FontWeight.Bold,
                textAlign     = TextAlign.Center,
                lineHeight    = 34.sp,
                letterSpacing = (-0.3).sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Subtitle
            Text(
                text       = subtitle,
                color      = OnboardingTheme.SubtitleGray,
                fontSize   = 13.sp,
                textAlign  = TextAlign.Center,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Pagination dots
            OnboardingDots(currentPage = currentPage, totalPages = totalPages)

            Spacer(modifier = Modifier.height(28.dp))

            // CTA Button
            Button(
                onClick  = onCtaClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .scale(buttonScale.value)
                    .alpha(buttonAlpha.value),
                shape  = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = OnboardingTheme.TealButton,
                    contentColor   = OnboardingTheme.White
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                Text(
                    text = ctaLabel,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.5.sp
                )
            }

            if (secondaryLabel != null && onSecondaryClick != null) {
                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (secondaryPrefix != null) {
                        Text(
                            text = secondaryPrefix,
                            color = OnboardingTheme.SubtitleGray,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }

                    Text(
                        text = secondaryLabel,
                        color = OnboardingTheme.TealButton,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable { onSecondaryClick() }
                    )
                }
            }
        }
    }
}

// ─── Pagination Dots ──────────────────────────────────────────────────────────
@Composable
fun OnboardingDots(
    currentPage: Int,
    totalPages: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier              = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment     = Alignment.CenterVertically
    ) {
        repeat(totalPages) { index ->
            val isActive = index == currentPage
            val dotWidth by animateDpAsState(
                targetValue   = if (isActive) 22.dp else 7.dp,
                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                label         = "dotWidth_$index"
            )
            Box(
                modifier = Modifier
                    .height(7.dp)
                    .width(dotWidth)
                    .clip(CircleShape)
                    .background(
                        if (isActive) OnboardingTheme.DotActive else OnboardingTheme.DotInactive
                    )
            )
        }
    }
}