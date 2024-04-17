package com.example.farmtrade.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmtrade.data.api.ChatWithGeminiService
import com.example.farmtrade.data.api.RetrofitInstance
import com.example.farmtrade.data.db.ChatResponse
import com.example.farmtrade.data.db.ChatUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ChatScreenViewModel(
        private val chatService: ChatWithGeminiService = RetrofitInstance.retrofit.create(ChatWithGeminiService::class.java)// Assuming injection or similar setup
) : ViewModel() {
    private val _conversation = MutableStateFlow(listOf(ChatUiModel.Message.initConv))
    val conversation: StateFlow<List<ChatUiModel.Message>> = _conversation


    fun sendChat(msg: String) {
        viewModelScope.launch {
            // Display the user's message in the UI immediately
            val myChat = ChatUiModel.Message(msg, ChatUiModel.Author.me)
            _conversation.emit(_conversation.value + myChat)


            // Send the message to the server and receive a response
            try {
                val response = chatService.chatWithGemini(msg)
                handleServerResponse(response)
            } catch (e: Exception) {
                // Handle errors, possibly update UI with an error message
                val errorChat = ChatUiModel.Message("Failed to fetch response", ChatUiModel.Author.bot)
                _conversation.emit(_conversation.value + errorChat)
                println("FAILED" + e.message)
            }
        }
    }

    private fun handleServerResponse(response: ChatResponse) {
        // Using safe calls and Elvis operator to provide a fallback mechanism
        val serverMessageText = response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text ?: "Server Error"
        println("RESPONSE")
        println(serverMessageText)


        viewModelScope.launch {
            // Emit the server message regardless of its content
            _conversation.emit(_conversation.value + ChatUiModel.Message(serverMessageText, ChatUiModel.Author.bot))
        }
    }
}