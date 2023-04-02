package com.example.application.level.figures

class FigureEasyCircle(
    diameter: Number,
    additionalZonePercent: Double = 0.5
):
    FigureCircle(diameter) {
    /** The same FigureCircle,
     *  but with additional invisible zone to accept click
     *
     *  А по русски)
     *  Это тот же круг, но с дополнительной невидимой зоной вокруг него,
     *  чтоб легче было кликнуть
     * */

    val clickableRadius = radius * (1 + additionalZonePercent)

    override fun isPointInsideFigure(x: Number, y: Number): Boolean {
        return getDistanceFromCenter(x, y) <= clickableRadius
    }
}