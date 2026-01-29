package com.android.thinkdecor.presentation.auth.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.android.thinkdecor.ui.theme.DangerPink
import com.android.thinkdecor.ui.theme.HintColor
import com.android.thinkdecor.ui.theme.PrimaryGreen
import com.android.thinkdecor.ui.theme.TitleColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    onSignUpClick: () -> Unit = {},
    onSignInClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onBackClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    AuthScaffold(onBackClick = onBackClick) {

        Spacer(Modifier.height(26.dp))

        Text(
            text = "Let’s Sign you in",
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold,
            color = TitleColor
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Lorem ipsum dolor sit amet, consectetur",
            fontSize = 14.sp,
            color = HintColor
        )

        Spacer(Modifier.height(32.dp))

        // Email
        Text("Email Address", fontSize = 14.sp, color = TitleColor)
        Spacer(Modifier.height(8.dp))
        FilledInput(
            value = email,
            placeholder = "Enter your email address",
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

        Spacer(Modifier.height(12.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = PrimaryGreen
                    )
                )
                Text("Remember Me", fontSize = 14.sp, color = HintColor)
            }

            Text(
                text = "Forgot Password",
                fontSize = 14.sp,
                color = DangerPink,
                modifier = Modifier.clickable { onForgotPasswordClick() }
            )
        }

        Spacer(Modifier.height(24.dp))

        if(email.isNotEmpty() && password.isNotEmpty()) {
            PrimaryButton(
                text = "Sign In",
                onClick = onSignInClick
            )
        }else{
            PrimaryButton(
                text = "Sign In",
                onClick = {},
                enabled = false
            )
        }

        Spacer(Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Don’t have an account? ", color = HintColor)
            Text(
                "Sign Up",
                color = PrimaryGreen,
                modifier = Modifier.clickable { onSignUpClick() }
            )
        }

        Spacer(Modifier.height(24.dp))

        DividerWithText("Or Sign In with")

        Spacer(Modifier.height(20.dp))

        Row(
            Modifier.fillMaxWidth(),
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

