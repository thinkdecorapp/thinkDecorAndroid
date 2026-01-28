package com.android.thinkdecor.presentation.auth.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.presentation.components.OTPTextField
import com.android.thinkdecor.ui.theme.BlackText
import com.android.thinkdecor.ui.theme.GrayText
import com.android.thinkdecor.ui.theme.TealPrimary

@Composable
fun PopUpScreen(
    onDismiss: () -> Unit = {},
    onVerifyClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Enter OTP",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = BlackText
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "A 4 digit OTP has been sent on your email address",
                    fontSize = 14.sp,
                    color = GrayText,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                // OTP Input Fields
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    repeat(4) { index ->
                        OTPTextField(
                            value = "",
                            onValueChange = {}
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = TealPrimary
                        )
                    ) {
                        Text("Dismiss", fontSize = 14.sp)
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(
                        onClick = onVerifyClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = TealPrimary),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Verify", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}