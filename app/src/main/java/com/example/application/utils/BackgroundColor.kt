package com.example.application.utils

import android.graphics.Color


enum class BackgroundColor(val rgb: Long) {
    WHITE(-1),
//    GRAY(0xFF676767),
    BLACK(0xFF000000),
    PINK(0xFFFD65F7),
    RED(0xFFFD6868),
    BLUE(0xFF5975FF),
    YELLOW(0xFFFFFF99),
    GREEN(0xFFB2FF66),
    AQUA(0xFF99FFFF),
    PURPLE(0xFFC495FF);

    fun isDark(): Boolean {
        val color = this.rgb.toInt()
        val darkness =
            1 - (0.299 * Color.red(color) +
                    0.587 * Color.green(color) +
                    0.114 * Color.blue(color)) / 255
        return darkness >= 0.5
    }
}