package com.example.application.level.utils

data class LevelSettings(
    val number: Int,
    var figuresLeft: Int = 0,
    var needRedraw: Boolean = false,
)