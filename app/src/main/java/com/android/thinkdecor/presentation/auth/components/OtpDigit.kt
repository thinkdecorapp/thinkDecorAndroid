package com.android.thinkdecor.presentation.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.presentation.ui.theme.GrayText
import com.android.thinkdecor.presentation.ui.theme.PrimaryGreen
import com.android.thinkdecor.presentation.ui.theme.TealPrimary

@Composable
fun OTPDigit(
    value: String,
    onValueChange: (String) -> Unit,
    isFocused: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
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
        textStyle = LocalTextStyle.current.copy(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = TealPrimary,
            unfocusedBorderColor = GrayText
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}
