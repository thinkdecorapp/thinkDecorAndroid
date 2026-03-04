package com.android.thinkdecor.presentation.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.presentation.chat.models.UiMessage


@Composable
fun MessageItem(message: UiMessage) {

    val SentBubbleShape = RoundedCornerShape(
        topStart = 18.dp,
        topEnd = 18.dp,
        bottomStart = 18.dp,
        bottomEnd = 4.dp
    )

    val ReceivedBubbleShape = RoundedCornerShape(
        topStart = 18.dp,
        topEnd = 18.dp,
        bottomStart = 4.dp,
        bottomEnd = 18.dp
    )

    val isMe = message.isMine

    Column(
        horizontalAlignment = if (isMe) Alignment.End else Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {

        Box(
            modifier = Modifier
                .background(
                    color = if (isMe) Color(0xFF0F6B5F) else Color(0xFFF1F3F6),
                    shape = if (isMe) SentBubbleShape else ReceivedBubbleShape
                )
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {

            Text(
                text = message.text,
                color = if (isMe) Color.White else Color.Black,
                fontSize = 14.sp
            )
        }

        Spacer(Modifier.height(4.dp))

        Text(
            text = message.time,
            fontSize = 11.sp,
            color = Color(0xFF9E9E9E),
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}

