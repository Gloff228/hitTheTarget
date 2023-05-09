package com.example.application.level.figures

import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import com.example.application.level.AbstractLevelActivity
import kotlin.random.Random
import com.example.application.utils.globalSettings

open class FigureBase() {
    var x = 0.0
    var y = 0.0
    lateinit var level: AbstractLevelActivity

    private var _isPressing = false // if pressed (inside figure) and still not released (anywhere)
    // TODO isHolding

    var isActive = false
    var needRedraw = true

    val paint = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private var color = globalSettings.targetColor.rgb.toInt()
    init {
        paint.color = color
    }

    open fun delete() {
        assert(isActive) // TODO: debug assertion. Need to delete this
        level.setNewActiveFigure()
    }

    fun setActive() {
        isActive = true
    }

    fun handleTouchEvent(event: MotionEvent, touchReached: Boolean = false): Boolean {
        val isInside = isPointInsideFigure(event.x, event.y)
        val canTouch = isInside && !touchReached  // if can touch this figure
        var touched = false

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                if (canTouch) {
                    onPressEvent()
                    touched = true
                }
                _isPressing = canTouch
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (canTouch) {
                    onReleaseEvent()
                    if (_isPressing) {
                        onClickEvent()
                    }
                    touched = true
                }
                _isPressing = false
            }
            MotionEvent.ACTION_MOVE -> {
                // pass
            }
        }
        return canTouch && touched
    }

    open fun setPosition(x: Number, y: Number) {
        val newX = x.toDouble()
        val newY = y.toDouble()
        if (this.x != newX || this.y != newY) {
            this.x = newX
            this.y = newY
            needRedraw = true
        }
    }

    fun setColor(color: Int) {
        if (this.color != color) {
            this.color = color
            paint.color = color
            needRedraw = true
        }
    }

    fun bindLevel(level: AbstractLevelActivity) {
        this.level = level
    }

    /** Ниже функции, которые можно переопределять для описания своей фигуры */

    open fun onPressEvent() {
        // define this
    }

    open fun onReleaseEvent() {
        // define this
    }

    open fun onClickEvent() {
        // define this

        // this.delete()
    }

    open fun isPointInsideFigure(x: Number, y: Number): Boolean {
        // redefine this
        return false
    }

    open fun calculateNewFrame(startTime: Long) {
        /** Calculates new parameters of the figure in this frame.
         *  Set 'needRedraw = true' here if you need to redraw this after calculations (for
         *  example if you change size/position)
         *
         */

        // redefine this

        // needRedraw = true
    }

    open fun draw(canvas: Canvas) {
        // redefine this

        // for example:
        // canvas.drawCircle(center.x.toFloat(), center.y.toFloat(), radius.toFloat(), paint)
    }
}