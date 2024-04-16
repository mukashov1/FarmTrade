package com.example.farmtrade.data.db

data class ChatResponse(
        val candidates: List<Candidate>
)

data class Candidate(
        val content: Content
)

data class Content(
        val role: String,
        val parts: List<Part>
)

data class Part(
        val text: String
)

