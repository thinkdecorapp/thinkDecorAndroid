package com.android.thinkdecor.presentation.auth.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.ui.theme.HintColor

@Composable
fun DividerWithText(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Divider(Modifier.weight(1f))
        Text(
            text,
            modifier = Modifier.padding(horizontal = 12.dp),
            fontSize = 12.sp,
            color = HintColor
        )
        Divider(Modifier.weight(1f))
    }
}
