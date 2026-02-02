package com.android.thinkdecor.presentation.auth.screens

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.presentation.auth.components.FilledInput
import com.android.thinkdecor.presentation.auth.components.PrimaryButton
import com.android.thinkdecor.presentation.navigation.AuthScaffold
import com.android.thinkdecor.presentation.ui.theme.BlackText
import com.android.thinkdecor.presentation.ui.theme.HintColor
import com.android.thinkdecor.presentation.ui.theme.TitleColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    onNextClick: (String) -> Unit = {},
    onBackClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }

    AuthScaffold(onBackClick = onBackClick) {

        Spacer(Modifier.height(26.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Forgot Password",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = BlackText,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Recover your account password",
                fontSize = 14.sp,
                color = HintColor
            )
        }

        Spacer(Modifier.height(32.dp))

        Text("E-mail", fontSize = 14.sp, color = TitleColor)
        Spacer(Modifier.height(8.dp))

        FilledInput(
            value = email,
            placeholder = "Enter your email",
            onValueChange = { email = it }
        )

        Spacer(Modifier.height(32.dp))

        PrimaryButton(
            text = "Next",
            onClick = { onNextClick(email) }
        )
    }
}
