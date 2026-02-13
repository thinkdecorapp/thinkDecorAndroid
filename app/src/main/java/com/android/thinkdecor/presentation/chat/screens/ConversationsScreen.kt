package com.android.thinkdecor.presentation.chat.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.presentation.auth.components.FilledInput
import com.android.thinkdecor.presentation.chat.components.ConversationRow
import com.android.thinkdecor.presentation.chat.mockData.FakeConversations.mockConversations
import com.android.thinkdecor.presentation.chat.models.UiConversation
import com.android.thinkdecor.R
import com.android.thinkdecor.presentation.navigation.AuthScaffold
import com.android.thinkdecor.presentation.ui.theme.HintColor

@Composable
@Preview(showBackground = true)
fun ConversationsScreen(
    items: List<UiConversation> = listOf(
        UiConversation("Victoria Avila", "That’s great, I look forward to hearing ba...", "11:20 am", 1, Color(0xFFB7D7C2)),
        UiConversation("Fitted - Tech & Design", "Thecla: @ovo How is it going?", "11:11 am", 0, Color(0xFF0F6B5F)),
        UiConversation("Demola Andreas", "Job Description.docx", "Yesterday", 1, Color(0xFF8FAADC)),
        UiConversation("Ibi Cookey", "How is it going?", "Yesterday", 0, Color(0xFFFFCDD2)),
        UiConversation("Thecla Ezenwa", "Please drop your morning update.", "Yesterday", 1, Color(0xFFFFE0B2)),
        UiConversation("Tobi Ozenua", "Aight, noted.", "Yesterday", 1, Color(0xFFD1C4E9)),
        UiConversation("Busola Ajala", "Aight, noted", "Yesterday", 1, Color(0xFFBBDEFB))
    ),
    onOpenChat: (UiConversation) -> Unit = {}
) {

    Spacer(Modifier.height(46.dp))
    Column(
        modifier = Modifier
            .background(Color.White)
    ) {
        Spacer(Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Conversations",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.newchat),
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 20.dp).size(20.dp)
            )
        }

        Spacer(Modifier.height(12.dp))

        HorizontalDivider(
            thickness = 0.5.dp
        )

        Spacer(Modifier.height(12.dp))

        Box(modifier = Modifier.padding(horizontal = 16.dp)) {

            var search by remember { mutableStateOf("") }

            ConversationSearchBar(
                value = search,
                onValueChange = { search = it },
                modifier = Modifier.padding(horizontal = 16.dp)
            )

        }

        Spacer(Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(items) { item ->
                ConversationRow(
                    item = item,
                    onClick = { onOpenChat(item) }
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
            .height(44.dp)
            .background(Color(0xFFF7F7FC), RoundedCornerShape(12.dp))
            .padding(horizontal = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = HintColor
        )

        Spacer(Modifier.width(8.dp))

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = Color.Black
            ),
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = "Search",
                        color = HintColor,
                        fontSize = 14.sp
                    )
                }
                innerTextField()
            }
        )
    }
}
