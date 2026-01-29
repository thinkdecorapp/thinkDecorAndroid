package com.android.thinkdecor.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.R

@Composable
fun HomeScreenDetails(
    onMenuClick: () -> Unit = {},
    onCartClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)
            .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
    ) {

        //Background Image - Chair with legs extending below
        Image(
            painter = painterResource(R.drawable.chair),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(480.dp) // Extended height so legs go below the container
                .align(Alignment.BottomCenter) // Align to bottom, centered horizontally
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Gray.copy(alpha = 0.3f),
                            Color.Gray.copy(alpha = 0.7f),
                            Color.Gray.copy(alpha = 0.9f)
                        )
                    )
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onMenuClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_humburger_icon),
                    contentDescription = null,
                    tint = Color.White
                )
            }

            CartIconWithBadge(
                count = 2,
                onClick = onCartClick
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 20.dp, top = 70.dp)
        ) {

            Text(
                "Check our",
                fontSize = 22.sp,
                color = Color.Black
            )

            Text(
                "AR FEATURE!",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                "Dining Chair",
                color = Color.Black
            )
        }
    }
}