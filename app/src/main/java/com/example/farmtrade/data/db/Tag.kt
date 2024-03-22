package com.example.farmtrade.data.db

enum class Tag(val title: String) {
    All("Смотреть все"),
    Face("kokonis"),
    Body("jemis"),
    Suntan("jem"),
    Mask("et")
}

val tags = Tag.values().toList()
