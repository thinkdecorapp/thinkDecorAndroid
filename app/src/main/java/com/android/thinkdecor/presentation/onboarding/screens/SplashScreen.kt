package com.android.thinkdecor.presentation.onboarding.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.R
import kotlinx.coroutines.delay

private val TealDark   = Color(0xFF004D45)
private val TealMid    = Color(0xFF00695C)
private val TealLight  = Color(0xFF00897B)
private val TealAccent = Color(0xFF26A69A)
private val White      = Color(0xFFFFFFFF)

@Composable
fun SplashScreen(
    onSplashComplete: @Composable () -> Unit = {}
) {

    onSplashComplete()

    val logoScale = remember { Animatable(0.6f) }
    val contentAlpha = remember { Animatable(0f) }
    val loaderProgress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        logoScale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness    = Spring.StiffnessMedium
            )
        )
        // Fade in text
        contentAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 500)
        )
        // Loading arc sweep
        loaderProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1200, easing = EaseInOutCubic)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(TealMid, TealDark)
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        DecorativeCircles()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .scale(logoScale.value),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = null,
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Dream Decor",
                color = White.copy(alpha = contentAlpha.value),
                fontSize = 20.sp,
                fontWeight  = FontWeight.Light,
                letterSpacing = 6.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(56.dp))

            CircularProgressIndicator(
                modifier = Modifier.size(36.dp),
                strokeWidth = 4.dp,
                color = Color.White,
                trackColor = Color.Transparent
            )
        }
    }
}

@Composable
private fun DecorativeCircles() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height

        // Big top-right arc
        drawCircle(
            color = Color.White.copy(alpha = 0.05f),
            radius = w * 0.95f,
            center = Offset(w * 1.15f, -h * 0.05f)
        )

        // Inner top-right arc
        drawCircle(
            color = Color.White.copy(alpha = 0.04f),
            radius = w * 0.75f,
            center = Offset(w * 1.10f, h * 0.05f)
        )

        // Bottom-left big arc
        drawCircle(
            color = Color.White.copy(alpha = 0.06f),
            radius = w * 0.85f,
            center = Offset(-w * 0.25f, h * 1.05f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}