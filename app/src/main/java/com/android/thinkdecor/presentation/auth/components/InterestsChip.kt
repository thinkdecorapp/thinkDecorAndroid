package com.android.thinkdecor.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.ui.theme.BlackText
import com.android.thinkdecor.ui.theme.GrayText
import com.android.thinkdecor.ui.theme.TealPrimary

@Composable
fun InterestChip(
    text: String,
    isSelected: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(48.dp)
            .border(
                width = 1.dp,
                color = if (isSelected) TealPrimary else GrayText.copy(alpha = 0.5f),
                shape = RoundedCornerShape(24.dp)
            )
            .background(
                color = if (isSelected) TealPrimary.copy(alpha = 0.1f) else Color.Transparent,
                shape = RoundedCornerShape(24.dp)
            )
            .clickable { onToggle() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = if (isSelected) TealPrimary else BlackText,
            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
        )
    }
}