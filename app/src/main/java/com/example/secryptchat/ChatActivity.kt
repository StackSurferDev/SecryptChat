package com.example.secryptchat

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChatActivity : AppCompatActivity() {

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var chatAdapter: ChatAdapter
    private val chatMessages = mutableListOf<ChatMessage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // Initialize views
        chatRecyclerView = findViewById(R.id.chatRecyclerView)
        val messageInputField = findViewById<EditText>(R.id.messageInputField)
        val sendButton = findViewById<ImageView>(R.id.sendButton)
        val addMediaButton = findViewById<ImageView>(R.id.addMediaButton)
        val photoButton = findViewById<ImageView>(R.id.photoButton)
        val videoCaptureButton = findViewById<ImageView>(R.id.videoCaptureButton)
        val microphoneButton = findViewById<ImageView>(R.id.microphoneButton)

        // Set up RecyclerView
        chatAdapter = ChatAdapter(chatMessages)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = chatAdapter

        // Send message logic
        sendButton.setOnClickListener {
            val messageText = messageInputField.text.toString().trim()
            if (messageText.isNotEmpty()) {
                sendMessage(messageText)
                messageInputField.text.clear()
            } else {
                Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show()
            }
        }

        // Add Media Action
        addMediaButton.setOnClickListener {
            Toast.makeText(this, "Add Media clicked", Toast.LENGTH_SHORT).show()
            // Placeholder: Trigger media picker or similar functionality
        }

        // Add Photo Action
        photoButton.setOnClickListener {
            Toast.makeText(this, "Add Photo clicked", Toast.LENGTH_SHORT).show()
            // Placeholder: Open gallery for photo selection
        }

        // Capture Video Action
        videoCaptureButton.setOnClickListener {
            Toast.makeText(this, "Capture Video clicked", Toast.LENGTH_SHORT).show()
            // Placeholder: Trigger video recording
        }

        // Record Audio Action
        microphoneButton.setOnClickListener {
            Toast.makeText(this, "Record Audio clicked", Toast.LENGTH_SHORT).show()
            // Placeholder: Trigger audio recording
        }

        // Simulate receiving a message (testing purposes)
        simulateIncomingMessage("Hello! How can I help you?")
    }

    private fun sendMessage(message: String) {
        // Add the message to the chat as sent by the user
        val userMessage = ChatMessage(message, isSentByUser = true)
        chatMessages.add(userMessage)
        chatAdapter.notifyItemInserted(chatMessages.size - 1)
        chatRecyclerView.scrollToPosition(chatMessages.size - 1)
    }

    private fun simulateIncomingMessage(message: String) {
        // Simulate receiving a message
        chatMessages.add(ChatMessage(message, isSentByUser = false))
        chatAdapter.notifyItemInserted(chatMessages.size - 1)
        chatRecyclerView.scrollToPosition(chatMessages.size - 1)
    }
}
