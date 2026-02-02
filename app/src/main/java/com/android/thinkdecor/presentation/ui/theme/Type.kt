package com.android.thinkdecor.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.R

// Set of Material typography styles to start with
val AppTypography = Typography(
    displayLarge = Typography().displayLarge.copy(
        fontFamily = PlusJakartaSans
    ),
    headlineMedium = Typography().headlineMedium.copy(
        fontFamily = PlusJakartaSans
    ),
    bodyMedium = Typography().bodyMedium.copy(
        fontFamily = PlusJakartaSans
    ),
    labelMedium = Typography().labelMedium.copy(
        fontFamily = PlusJakartaSans
    )
)