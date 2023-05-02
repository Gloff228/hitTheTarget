package com.example.application.utils

data class StarSettings(
    var scale: Float = 6f,
    var size: Int = 60,
    var duration: Long = 1000,
    var maxCount: Int = 10
)

val starSettings = StarSettings()
