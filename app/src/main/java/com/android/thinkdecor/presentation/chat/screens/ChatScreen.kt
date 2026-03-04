package com.android.thinkdecor.presentation.chat.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.presentation.chat.components.MessageItem
import com.android.thinkdecor.presentation.chat.mockData.FakeMessages.mockChatMessages
import com.android.thinkdecor.presentation.chat.models.UiMessage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen(
        userName = "Demola Andreas",
        messages = mockChatMessages
    )
}

@Composable
fun ChatScreen(
    userName: String,
    messages: List<UiMessage>,
    onBack: () -> Unit = {}
) {
    val messageList = remember { mutableStateListOf<UiMessage>().apply { addAll(messages) } }

    var inputText by remember { mutableStateOf("") }

    val listState = rememberLazyListState()
    LaunchedEffect(messageList.size) {
        if (messageList.isNotEmpty()) {
            listState.animateScrollToItem(messageList.lastIndex)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        containerColor = Color(0xFFF7F8FA)
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 8.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBackIos,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Color.Black
                    )
                }
                Text(
                    text = userName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f),
                    color = Color.Black
                )
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Search, contentDescription = null, tint = Color.Black)
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Menu, contentDescription = null, tint = Color.Black)
                }
            }

            HorizontalDivider(thickness = 0.5.dp, color = Color(0xFFEAEAEA))

            Spacer(Modifier.size(12.dp))

            // ── Messages ──────────────────────────────────────────────────────
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = messageList,
                    key = { it.id }   // UiMessage needs an `id` field — see note below
                ) { msg ->
                    MessageItem(message = msg)
                }
            }

            // ── Input Bar ─────────────────────────────────────────────────────
            ChatInputBar(
                value = inputText,
                onValueChange = { inputText = it },
                onSend = {
                    val trimmed = inputText.trim()
                    if (trimmed.isNotEmpty()) {
                        messageList.add(
                            UiMessage(
                                id = System.currentTimeMillis().toString(),
                                text = trimmed,
                                isMine = true,
                                time = SimpleDateFormat(
                                    "hh:mm a", Locale.getDefault()
                                ).format(Date())
                            )
                        )
                        inputText = ""
                    }
                }
            )
        }
    }
}

@Composable
fun ChatInputBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSend: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Text field
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = false,
            maxLines = 4,
            textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
            modifier = Modifier
                .weight(1f)
                .background(Color(0xFFF1F3F6), RoundedCornerShape(20.dp))
                .padding(horizontal = 16.dp, vertical = 10.dp),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = "Type a message…",
                        color = Color(0xFF9E9E9E),
                        fontSize = 14.sp
                    )
                }
                innerTextField()
            }
        )

        Spacer(Modifier.size(8.dp))

        val canSend = value.trim().isNotEmpty()
        IconButton(
            onClick = onSend,
            enabled = canSend,
            modifier = Modifier
                .size(42.dp)
                .background(
                    color = if (canSend) Color(0xFF0F6B5F) else Color(0xFFDDDDDD),
                    shape = RoundedCornerShape(50)
                )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Send",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}