package com.android.thinkdecor.presentation.auth.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.presentation.auth.components.FilledInput
import com.android.thinkdecor.presentation.auth.components.PrimaryButton
import com.android.thinkdecor.presentation.navigation.AuthScaffold
import com.android.thinkdecor.ui.theme.HintColor
import com.android.thinkdecor.ui.theme.PrimaryGreen
import com.android.thinkdecor.ui.theme.TitleColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNewPasswordScreen(
    email: String = "",
    onNextClick: () -> Unit = {},
    onBackClick: () -> Unit
) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var newPasswordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    AuthScaffold(onBackClick = onBackClick) {
        Spacer(Modifier.height(26.dp))

        Text(
            text = "Create a\nNew Password",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = TitleColor
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Enter your new password",
            fontSize = 14.sp,
            color = HintColor
        )

        Spacer(Modifier.height(32.dp))

        Text("New Password", fontSize = 14.sp, color = TitleColor)
        Spacer(Modifier.height(8.dp))
        FilledInput(
            value = newPassword,
            placeholder = "Enter new password",
            onValueChange = { newPassword = it },
            isPassword = true,
            passwordVisible = newPasswordVisible,
            onToggleVisibility = { newPasswordVisible = !newPasswordVisible }
        )

        Spacer(Modifier.height(20.dp))

        Text("Confirm Password", fontSize = 14.sp, color = TitleColor)
        Spacer(Modifier.height(8.dp))
        FilledInput(
            value = confirmPassword,
            placeholder = "Confirm your password",
            onValueChange = { confirmPassword = it },
            isPassword = true,
            passwordVisible = confirmPasswordVisible,
            onToggleVisibility = { confirmPasswordVisible = !confirmPasswordVisible }
        )

        Spacer(Modifier.height(32.dp))

        PrimaryButton(
            text = "Next",
            onClick = onNextClick
        )
    }
}

