package com.android.thinkdecor.presentation.auth.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.presentation.auth.components.OTPDigit
import com.android.thinkdecor.presentation.auth.components.PrimaryButton
import com.android.thinkdecor.presentation.navigation.AuthScaffold
import com.android.thinkdecor.ui.theme.HintColor
import com.android.thinkdecor.ui.theme.PrimaryGreen
import com.android.thinkdecor.ui.theme.TealPrimary
import com.android.thinkdecor.ui.theme.TitleColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterOTPScreen(
    email: String,
    onContinueClick: () -> Unit = {},
    onResendClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val otp = listOf("3", "3", "1", "4")

    val otpComplete = otp.all { it.isNotEmpty() }

    AuthScaffold(onBackClick = onBackClick) {

        Spacer(Modifier.height(26.dp))

        Text(
            "Enter OTP",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = TitleColor
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "We have just sent you 4 digit code via your\nemail $email",
            fontSize = 14.sp,
            color = HintColor,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            otp.forEachIndexed { index, digit ->
                OTPDigit(
                    value = digit,
                    isFocused = index == 3
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        PrimaryButton(
            text = "Continue",
            enabled = otpComplete,
            onClick = onContinueClick
        )

        Spacer(Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Didn’t receive code? ", color = HintColor)
            Text(
                "Resend Code",
                color = PrimaryGreen,
                modifier = Modifier.clickable { onResendClick() }
            )
        }
    }
}

