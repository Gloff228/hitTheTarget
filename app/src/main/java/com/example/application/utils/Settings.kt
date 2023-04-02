package com.example.application.utils

data class Settings(
    var difficulty: Difficulty = Difficulty.NORMAL,
    var backgroundColor: BackgroundColor = BackgroundColor.WHITE,
)

val globalSettings = Settings()
