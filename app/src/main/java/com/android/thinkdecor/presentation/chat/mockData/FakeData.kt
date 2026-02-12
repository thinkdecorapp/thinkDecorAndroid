package com.android.thinkdecor.presentation.chat.mockData

import androidx.compose.ui.graphics.Color
import com.android.thinkdecor.presentation.chat.models.UiAttachment
import com.android.thinkdecor.presentation.chat.models.UiConversation
import com.android.thinkdecor.presentation.chat.models.UiMessage

object FakeConversations {
    val mockConversations = listOf(
        UiConversation("Victoria Avila", "That’s great, I look forward to hearing ba...", "11:20 am", 1, Color(0xFFB7D7C2)),
        UiConversation("Fitted - Tech & Design", "Thecla: @ovo How is it going?", "11:11 am", 0, Color(0xFF0F6B5F)),
        UiConversation("Demola Andreas", "Job Description.docx", "Yesterday", 1, Color(0xFF8FAADC)),
        UiConversation("Ibi Cookey", "How is it going?", "Yesterday", 0, Color(0xFFFFCDD2)),
        UiConversation("Thecla Ezenwa", "Please drop your morning update.", "Yesterday", 1, Color(0xFFFFE0B2)),
        UiConversation("Tobi Ozenua", "Aight, noted.", "Yesterday", 1, Color(0xFFD1C4E9)),
        UiConversation("Busola Ajala", "Aight, noted", "Yesterday", 1, Color(0xFFBBDEFB))
    )
}

object FakeMessages {
    val mockChatMessages = listOf(
        UiMessage(
            id = "1",
            text = "Hi Abiola, any progress on the project?\nWe need a link for standup.",
            isMine = true,
            time = "1 day ago"
        ),
        UiMessage(
            id = "2",
            text = "Hi Demola!\nYes, I just finished developing the \"Chat\" template.",
            isMine = false,
            time = "1 day ago"
        ),
        UiMessage(
            id = "3",
            text = "",
            isMine = false,
            time = "2 min ago",
            attachment = UiAttachment(
                fileName = "Job Description.docx",
                sizeKb = 487
            )
        ),
        UiMessage(
            id = "4",
            text = "Hello Demola,\nWhat job role is this document is this for?",
            isMine = true,
            time = "1 min ago"
        ),
        UiMessage(
            id = "5",
            text = "Technical Project Manager role.\nHybrid, 3 days on site a week.",
            isMine = false,
            time = "1 min ago"
        )
    )
}
