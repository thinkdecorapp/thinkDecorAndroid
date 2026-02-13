package com.android.thinkdecor.presentation.chat.models

import androidx.compose.ui.graphics.Color

data class UiConversation(
    val name: String,
    val lastMessage: String,
    val time: String,
    val unreadCount: Int,
    val avatarColor: Color
)

data class UiAttachment(
    val fileName: String,
    val sizeKb: Int
)

data class UiMessage(
    val id: String,
    val text: String,
    val isMine: Boolean,
    val time: String,
    val attachment: UiAttachment? = null
)

