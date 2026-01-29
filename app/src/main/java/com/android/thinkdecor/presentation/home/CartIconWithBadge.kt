package com.android.thinkdecor.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.R

@Composable
fun CartIconWithBadge(
    count: Int,
    onClick: () -> Unit
) {

    Box {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_shopping_cart),
                contentDescription = null,
                tint = Color.White
            )
        }

        if (count > 0) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.TopEnd)
                    .background(Color(0xFF0F7A5C), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    count.toString(),
                    fontSize = 10.sp,
                    color = Color.White
                )
            }
        }
    }
}