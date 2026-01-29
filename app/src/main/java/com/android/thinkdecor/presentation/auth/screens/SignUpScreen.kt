package com.android.thinkdecor.presentation.auth.screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.R
import com.android.thinkdecor.presentation.auth.components.DividerWithText
import com.android.thinkdecor.presentation.auth.components.FilledInput
import com.android.thinkdecor.presentation.auth.components.PrimaryButton
import com.android.thinkdecor.presentation.auth.components.SocialLoginButton
import com.android.thinkdecor.presentation.navigation.AuthScaffold
import com.android.thinkdecor.presentation.ui.theme.HintColor
import com.android.thinkdecor.presentation.ui.theme.TitleColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onBackClick: () -> Unit = {},
    onSignUpClick: (String) -> Unit = {}
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    AuthScaffold(onBackClick = onBackClick) {

        Spacer(Modifier.height(26.dp))

        Text(
            text = "Create Account",
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold,
            color = TitleColor
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Enter your details to create a new account.",
            fontSize = 14.sp,
            color = HintColor
        )

        Spacer(Modifier.height(32.dp))

        // Full Name
        Text("Full Name", fontSize = 14.sp, color = TitleColor)
        Spacer(Modifier.height(8.dp))
        FilledInput(
            value = fullName,
            placeholder = "Enter your name",
            onValueChange = { fullName = it }
        )

        Spacer(Modifier.height(20.dp))

        // Email
        Text("E-mail", fontSize = 14.sp, color = TitleColor)
        Spacer(Modifier.height(8.dp))
        FilledInput(
            value = email,
            placeholder = "Enter your email",
            onValueChange = { email = it }
        )

        Spacer(Modifier.height(20.dp))

        // Password
        Text("Password", fontSize = 14.sp, color = TitleColor)
        Spacer(Modifier.height(8.dp))
        FilledInput(
            value = password,
            placeholder = "Enter your password",
            onValueChange = { password = it },
            isPassword = true,
            passwordVisible = passwordVisible,
            onToggleVisibility = { passwordVisible = !passwordVisible }
        )

        Spacer(Modifier.height(28.dp))

        val isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isFormValid =
            fullName.trim().split(" ").size >= 2 && isEmailValid && password.length >= 6

        PrimaryButton(
            "Create An Account",
            onClick = { onSignUpClick(email) },
            enabled = isFormValid
        )

        Spacer(Modifier.height(24.dp))

        DividerWithText("Or Sign In with")

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SocialLoginButton(painterResource(R.drawable.google)) {}
            SocialLoginButton(painterResource(R.drawable.apple)) {}
            SocialLoginButton(painterResource(R.drawable.facebook)) {}
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = "By signing up you agree to our Terms\nand Conditions of Use",
            fontSize = 12.sp,
            color = HintColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
