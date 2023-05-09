package com.example.application.utils

data class StarSettings(
    var scale: Float = 3f,
    var size: Int = 100,
    var duration: Long = 1000,
    var maxCount: Int = 20
)

val starSettings = StarSettings()
