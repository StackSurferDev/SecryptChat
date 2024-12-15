package com.example.secryptchat

data class ChatMessage(
    val message: String,
    val isSentByUser: Boolean // True if sent by the user, false if received
)

