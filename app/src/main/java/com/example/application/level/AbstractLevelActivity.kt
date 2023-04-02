package com.example.application.level

import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import com.example.application.MyActivity
import com.example.application.R
import com.example.application.level.figures.FigureBase


open class AbstractLevelActivity : MyActivity(), SurfaceHolder.Callback2 {
    private val FPS = 60

    private val frameDurationMs = 1000 / FPS

    private val lock = Object()
    @Volatile var canRun = false
    @Volatile var threadQuit = false

    lateinit var surface: SurfaceView

    var figures = mutableListOf<FigureBase>()

    @RequiresApi(Build.VERSION_CODES.O)
    private var drawingThread = Thread {
        while (!threadQuit) {
            if (!canRun) {
                synchronized(lock) {
                    try {
                        lock.wait()
                    } catch (e: Exception) {
                        // pass
                    }
                }
            }
            val startTime = System.currentTimeMillis()
            try {
                // Start drawing
                handleNewFrame(startTime)
                val drawTime = System.currentTimeMillis() - startTime

                if (drawTime < frameDurationMs) {
                    Thread.sleep(frameDurationMs - drawTime)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun calculateNewFrame(startTime: Long): Boolean {
        /** Returns true, if redraw is needed after calculations */

        lateinit var figure: FigureBase
        var needRedraw = false
        for (i in figures.size - 1 downTo 0) {
            figure = figures[i]
            if (figure.isExists()) {
                figure.calculateNewFrame(startTime)
            }

            if (figure.needRedraw) {
                needRedraw = true
                figure.needRedraw = false
            }
        }
        return needRedraw
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun drawNewFrame() {
        println("Redraw")
        val canvas = surface.holder.lockCanvas()
        if (canvas != null) {
            canvas.drawColor(settings.backgroundColor.rgb.toInt())
            // canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
            for (figure in figures) {
                if (figure.isExists()) {
                    figure.draw(canvas)
                }
            }
        }
        surface.holder.unlockCanvasAndPost(canvas)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun handleNewFrame(startTime: Long) {
        val needRedraw = calculateNewFrame(startTime)
        onHandlingNewFrame()
        if (needRedraw) {
            drawNewFrame()
        }
    }

    open fun onHandlingNewFrame() {
        // pass
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        setContentView(R.layout.activity_abstract_level)

        surface = findViewById(R.id.surface)
        surface.holder.addCallback(this)
        surface.holder.setFormat(PixelFormat.RGBA_8888)
        drawingThread.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        threadQuit = true
        canRun = true
        try {
            synchronized(lock) {
                lock.notify()
            }
        } catch (e: Exception) {
            // pass
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        /** Always returns true */

        if (event === null) return true

        var touchReached = false
        for (i in figures.size - 1 downTo 0) {
            val figure = figures[i]
            if (!figure.isExists()) continue
            if (figure.handleTouchEvent(event, touchReached)) {
                touchReached = true
            }
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        threadQuit = false
        canRun = true
        try {
            synchronized(lock) {
                lock.notify()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        // pass
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        canRun = false
    }

    override fun surfaceRedrawNeeded(p0: SurfaceHolder) {
        // pass
    }

    fun clearRubbishFigures() {
        /** Deleting figures by one at a time is quite slow operation.
         *  It's better to remain some rubbish figures and then delete them
         *  all at once after some time
         * */
        figures = figures.filter { figure -> figure.isExists() }.toMutableList()
    }


}