package com.android.thinkdecor.presentation.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.presentation.chat.models.UiMessage
import com.android.thinkdecor.presentation.ui.theme.HintColor
import com.android.thinkdecor.presentation.ui.theme.PrimaryGreen


@Composable
fun MessageItem(message: UiMessage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.isMine) Alignment.End else Alignment.Start
    ) {
        MessageBubble(message = message)

        Spacer(Modifier.height(4.dp))

        Text(
            text = message.time,
            fontSize = 11.sp,
            color = HintColor,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Composable
fun MessageBubble(message: UiMessage) {
    val bubbleColor = if (message.isMine) PrimaryGreen else Color.White
    val textColor = if (message.isMine) Color.White else Color.Black

    Column(
        modifier = Modifier
            .background(
                bubbleColor,
                RoundedCornerShape(16.dp)
            )
            .widthIn(max = 280.dp)
    ) {
        // Attachment card
        message.attachment?.let { att ->
            Column(
                modifier = Modifier
                    .background(Color(0xFFE9F0EE), RoundedCornerShape(16.dp))
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(att.fileName, fontWeight = FontWeight.SemiBold, color = Color.Black)
                    Text("${att.sizeKb} KB", fontSize = 12.sp, color = Color.Red)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(PrimaryGreen)
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Download", color = Color.White, fontWeight = FontWeight.SemiBold)
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }

        if (message.text.isNotEmpty()) {
            Text(
                text = message.text,
                color = textColor,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}
