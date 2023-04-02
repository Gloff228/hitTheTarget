package com.example.application.level.figures

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent
import kotlin.random.Random

open class FigureBase {
    var x = 0.0
    var y = 0.0

    private var _isPressing = false // if pressed (inside figure) and still not released (anywhere)
    // TODO isHolding

    var _isExists = true
    var needRedraw = true

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var color = Color.RED

    init {
        paint.color = color

        x = Random.nextInt(0, 1500).toDouble()
        y = Random.nextInt(0, 500).toDouble()
        setColor(-Random.nextInt(0, 16777216))
    }

    fun isExists(): Boolean = _isExists

    open fun delete() {
        /** Figure becomes invisible and untouchable
         *  Actual deleting will be after AbstractLevelActivity.clearRubbishFigures()
         * */
        if (_isExists) {
            needRedraw = true
        }
        _isExists = false
    }

    open fun onPressEvent() {
        // define this
    }

    open fun onReleaseEvent() {
        // define this
    }

    open fun onClickEvent() {
        // define this
        this.delete()
    }

    open fun isPointInsideFigure(x: Number, y: Number): Boolean {
        // redefine this
        return false
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
    }
}