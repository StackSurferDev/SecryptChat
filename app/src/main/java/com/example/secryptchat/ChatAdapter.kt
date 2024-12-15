package com.example.secryptchat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val messages: List<ChatMessage>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    inner class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageText: TextView = view.findViewById(R.id.messageText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_message_item, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val message = messages[position]
        holder.messageText.text = message.message

        // Align messages based on sender
        if (message.isSentByUser) {
            holder.messageText.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            holder.messageText.setBackgroundResource(R.drawable.message_bubble_user)
        } else {
            holder.messageText.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            holder.messageText.setBackgroundResource(R.drawable.message_bubble_received)
        }
    }

    override fun getItemCount(): Int = messages.size
}
