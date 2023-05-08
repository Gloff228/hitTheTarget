package com.example.application.level.utils

data class LevelSettingsItem(
    var name: String,
    var minValue: Int,
    var maxValue: Int,
    var step: Int = 1,
    var startValue: Int = (maxValue + minValue) / 2
)
