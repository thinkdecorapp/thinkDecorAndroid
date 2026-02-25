package com.android.thinkdecor.presentation.onboarding.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.presentation.onboarding.screens.OnboardingDots

object OnboardingTheme {
    val BackgroundDark = Color(0xFF141414)
    val TealButton = Color(0xFF00695C)
    val White = Color(0xFFFFFFFF)
    val SubtitleGray = Color(0xFFAAAAAA)
}


@Composable
fun OnboardingLayout(
    imageResId: Int,
    title: String,
    subtitle: String,
    currentPage: Int,
    totalPages: Int,
    ctaLabel: String,
    onCtaClick: () -> Unit,
    secondaryPrefix: String? = null,
    secondaryLabel: String? = null,
    onSecondaryClick: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OnboardingTheme.BackgroundDark)
    ) {
        // Simple crossfade for image
        Crossfade(
            targetState = imageResId,
            animationSpec = tween(300)
        ) { img ->
            Image(
                painter = painterResource(id = img),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.0f to Color.Transparent,
                            0.45f to OnboardingTheme.BackgroundDark.copy(alpha = 0.35f),
                            0.70f to OnboardingTheme.BackgroundDark.copy(alpha = 0.75f),
                            1.0f to OnboardingTheme.BackgroundDark
                        )
                    )
                )
        )

        AnimatedContent(
            targetState = currentPage,
            transitionSpec = {
                fadeIn(tween(250)) + slideInVertically(tween(250)) { 20 } togetherWith
                        fadeOut(tween(150)) + slideOutVertically(tween(150)) { -20 }
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            BottomContent(
                title = title,
                subtitle = subtitle,
                currentPage = it,
                totalPages = totalPages,
                ctaLabel = ctaLabel,
                onCtaClick = onCtaClick,
                secondaryPrefix = secondaryPrefix,
                secondaryLabel = secondaryLabel,
                onSecondaryClick = onSecondaryClick
            )
        }
    }
}

@Composable
private fun BottomContent(
    title: String,
    subtitle: String,
    currentPage: Int,
    totalPages: Int,
    ctaLabel: String,
    onCtaClick: () -> Unit,
    secondaryPrefix: String?,
    secondaryLabel: String?,
    onSecondaryClick: (() -> Unit)?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp)
            .padding(bottom = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Title
        Text(
            text = title,
            color = OnboardingTheme.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 34.sp,
            letterSpacing = (-0.3).sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Subtitle
        Text(
            text = subtitle,
            color = OnboardingTheme.SubtitleGray,
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Dots
        OnboardingDots(
            currentPage = currentPage,
            totalPages = totalPages
        )

        Spacer(modifier = Modifier.height(28.dp))

        // CTA Button
        Button(
            onClick = onCtaClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = OnboardingTheme.TealButton,
                contentColor = OnboardingTheme.White
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

        // Secondary action (only on last page)
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
