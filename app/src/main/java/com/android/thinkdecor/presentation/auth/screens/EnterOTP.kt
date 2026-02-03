package com.android.thinkdecor.presentation.auth.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.thinkdecor.presentation.auth.components.OTPDigit
import com.android.thinkdecor.presentation.auth.components.OTPTextField
import com.android.thinkdecor.presentation.auth.components.PrimaryButton
import com.android.thinkdecor.presentation.auth.utils.isOtpValid
import com.android.thinkdecor.presentation.navigation.AuthScaffold
import com.android.thinkdecor.presentation.ui.theme.BlackText
import com.android.thinkdecor.presentation.ui.theme.HintColor
import com.android.thinkdecor.presentation.ui.theme.PrimaryGreen

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun EnterOTPScreen(
    email: String = "aditya1875more@gmail.com",
    onContinueClick: () -> Unit = {},
    onResendClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    var otp by remember { mutableStateOf(listOf("", "", "", "")) }

    val otpComplete = isOtpValid(otp.joinToString(""))

    AuthScaffold(onBackClick = onBackClick) {

        Spacer(Modifier.height(46.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Enter OTP",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = BlackText,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = buildAnnotatedString {
                    append("We have just sent you 4 digit code via your\nemail ")

                    withStyle(
                        style = SpanStyle(color = BlackText)
                    ) {
                        append(email)
                    }
                },
                style = MaterialTheme.typography.bodyMedium,
                color = HintColor,
                textAlign = TextAlign.Center
            )
        }

        Spacer(Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            repeat(4) { index ->
                OTPDigit(
                    value = otp[index],
                    onValueChange = { value ->
                        if (value.length <= 1) {
                            otp = otp.toMutableList().apply { this[index] = value }
                        }
                    },
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        PrimaryButton(
            text = "Continue",
            enabled = otpComplete,
            onClick = {
                onContinueClick()
            }
        )

        Spacer(Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Didn’t receive code? ",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = HintColor
            )

            Text(
                text = "Resend Code",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryGreen,
                modifier = Modifier.clickable { onResendClick() }
            )
        }
    }
}


