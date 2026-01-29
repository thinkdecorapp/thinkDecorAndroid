package com.android.thinkdecor.presentation.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.presentation.ui.theme.PrimaryGreen
import com.android.thinkdecor.presentation.ui.theme.TitleColor

@Composable
fun OTPDigit(
    value: String,
    isFocused: Boolean
) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .background(
                color = Color(0xFFF6F6F6),
                shape = CircleShape
            )
            .border(
                width = if (isFocused) 1.5.dp else 0.dp,
                color = if (isFocused) PrimaryGreen else Color.Transparent,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = TitleColor
        )
    }
}
