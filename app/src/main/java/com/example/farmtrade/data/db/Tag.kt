package com.example.farmtrade.data.db

enum class Tag(val title: String) {
    All("Смотреть все"),
    Face("Kokonis"),
    Body("Jemis"),
    Suntan("Jem"),
    Mask("Et")
}

val tags = Tag.values().toList()
