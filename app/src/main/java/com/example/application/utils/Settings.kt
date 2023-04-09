package com.example.application.utils

data class Settings(
    var difficulty: Difficulty = Difficulty.NORMAL,
    var backgroundColor: BackgroundColor = BackgroundColor.WHITE,
    var targetColor: TargetColor = TargetColor.RED,
    var obvodkaColor: TargetColor = TargetColor.WHITE
)

val globalSettings = Settings()
