package com.android.thinkdecor.presentation.auth.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.thinkdecor.presentation.auth.components.OTPDigit
import com.android.thinkdecor.presentation.auth.components.PrimaryButton
import com.android.thinkdecor.presentation.navigation.AuthScaffold
import com.android.thinkdecor.presentation.ui.theme.HintColor
import com.android.thinkdecor.presentation.ui.theme.PrimaryGreen
import com.android.thinkdecor.presentation.ui.theme.TitleColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterOTPScreen(
    email: String,
    onContinueClick: () -> Unit = {},
    onResendClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    var otpValue by remember { mutableStateOf("") }
    val otpLength = 4
    val otpComplete = otpValue.length == otpLength

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
            text = "We have just sent you $otpLength digit code via your\nemail $email",
            fontSize = 14.sp,
            color = HintColor,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(32.dp))

        Box(contentAlignment = Alignment.Center) {
            // Hidden TextField to capture input
            BasicTextField(
                value = otpValue,
                onValueChange = {
                    if (it.length <= otpLength && it.all { char -> char.isDigit() }) {
                        otpValue = it
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .alpha(0f) // Make it invisible
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(otpLength) { index ->
                    val digit = if (index < otpValue.length) otpValue[index].toString() else ""
                    val isFocused =
                        index == otpValue.length || (index == otpLength - 1 && otpComplete)
                    OTPDigit(
                        value = digit,
                        isFocused = isFocused
                    )
                }
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

