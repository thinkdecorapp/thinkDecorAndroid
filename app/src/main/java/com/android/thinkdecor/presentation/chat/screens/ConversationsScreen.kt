package com.android.thinkdecor.presentation.chat.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.android.thinkdecor.presentation.chat.mockData.FakeConversations
import com.android.thinkdecor.presentation.chat.models.UiConversation
import kotlin.random.Random

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ConversationsScreen(
    items: List<UiConversation> = FakeConversations.mockConversations,
    onOpenChat: (UiConversation) -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF7F8FA)
    ) { paddingValues ->

        var search by remember { mutableStateOf("") }

        val filteredItems = remember(search, items) {
            if (search.isBlank()) items
            else items.filter { conversation ->
                conversation.name.contains(search, ignoreCase = true) ||
                        conversation.lastMessage.contains(search, ignoreCase = true)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Conversations",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.3).sp,
                    color = Color.Black
                )
            }

            Spacer(Modifier.height(16.dp))

            HorizontalDivider(thickness = 0.5.dp, color = Color(0xFFEAEAEA))

            Spacer(Modifier.height(16.dp))

            ConversationSearchBar(
                value = search,
                onValueChange = { search = it },
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(12.dp))

            if (filteredItems.isEmpty()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No conversations found",
                        color = Color(0xFF9E9E9E),
                        fontSize = 14.sp
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(
                        items = filteredItems,
                        key = { it.name }
                    ) { item ->
                        ConversationRow(
                            item    = item,
                            onClick = { onOpenChat(item) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ConversationRow(
    item: UiConversation,
    onClick: () -> Unit
) {
    val randomImageUrl = remember {
        "https://picsum.photos/200?random=${Random.nextInt(1, 1000)}"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model              = randomImageUrl,
            contentDescription = null,
            modifier           = Modifier.size(48.dp).clip(CircleShape),
            contentScale       = ContentScale.Crop
        )

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier              = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text       = item.name,
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color      = Color.Black
                )
                Text(
                    text     = item.time,
                    fontSize = 12.sp,
                    color    = Color(0xFF9E9E9E)
                )
            }

            Spacer(Modifier.height(4.dp))

            Text(
                text     = item.lastMessage,
                fontSize = 14.sp,
                color    = Color(0xFF6E6E6E),
                maxLines = 1
            )
        }

        if (item.unreadCount > 0) {
            Spacer(Modifier.width(8.dp))
            Box(
                modifier        = Modifier.size(20.dp).background(Color(0xFF0F6B5F), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text       = item.unreadCount.toString(),
                    fontSize   = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color      = Color.White
                )
            }
        }
    }
}

@Composable
fun ConversationSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color(0xFFF1F3F6), RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = Color(0xFF9E9E9E)
        )

        Spacer(Modifier.width(8.dp))

        BasicTextField(
            value         = value,
            onValueChange = onValueChange,
            singleLine    = true,
            textStyle     = TextStyle(fontSize = 14.sp, color = Color.Black),
            modifier      = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(text = "Search", color = Color(0xFF9E9E9E), fontSize = 14.sp)
                }
                innerTextField()
            }
        )
    }
}