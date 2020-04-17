package com.example.go.piano_pitch.ui.view.piano

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PianoView(
    context: Context,
    attrs: AttributeSet
) : View(context, attrs) {

    companion object {
        private const val N_WHITE_KEY = 14
        private const val START_NOTE_NUMBER = 60

        private const val BLACK_WIDTH = 0.7f
        private const val BLACK_HEIGHT = 0.6f
    }

    private val blackPaint = Paint().apply {
        color = Color.BLACK
    }
    private val whitePaint = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }
    private val yellowPaint = Paint().apply {
        color = Color.YELLOW
        style = Paint.Style.FILL
    }

    private var keyWidth: Float = 0f
    private var keyHeight: Float = 0f

    private val whiteKeys: ArrayList<Key> = arrayListOf()
    private val blackKeys: ArrayList<Key> = arrayListOf()

    private lateinit var pianoPlayer: PianoPlayer

    private var onPlayListener: OnPlayListener? = null

    private var isTouchable = false

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        keyWidth = w.toFloat() / N_WHITE_KEY
        keyHeight = h.toFloat()
        var noteNumber =
            START_NOTE_NUMBER

        for (i in 0 until N_WHITE_KEY) {
            var left = i * keyWidth
            var right = if (i != N_WHITE_KEY) left + keyWidth else w.toFloat()
            whiteKeys.add(
                Key(
                    noteNumber++,
                    RectF(left, 0f, right, keyHeight)
                )
            )
            if (i % 7 in listOf(2, 6)) continue

            left = (i + (1 - BLACK_WIDTH / 2)) * keyWidth
            right = left + keyWidth * BLACK_WIDTH
            val height = keyHeight * BLACK_HEIGHT
            blackKeys.add(
                Key(
                    noteNumber++,
                    RectF(left, 0f, right, height)
                )
            )
        }
    }

    override fun onDraw(canvas: Canvas?) {
        for (k in whiteKeys) {
            canvas?.drawRect(k.rect, if (k.isDown) yellowPaint else whitePaint)
        }

        for (i in 1 until N_WHITE_KEY) {
            canvas?.drawLine(i * keyWidth, 0f, i * keyWidth, keyHeight, blackPaint)
        }

        for (k in blackKeys) {
            canvas?.drawRect(k.rect, if (k.isDown) yellowPaint else blackPaint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (!isTouchable) return false

        checkNotNull(event)
        val action = event.actionMasked
        val isDown = action in listOf(
            MotionEvent.ACTION_DOWN,
            MotionEvent.ACTION_MOVE,
            MotionEvent.ACTION_POINTER_DOWN
        )

        if (action == MotionEvent.ACTION_POINTER_UP) {
            val pointerIndex = event.actionIndex
            val x = event.getX(pointerIndex)
            val y = event.getY(pointerIndex)

            val key = getDownKey(x, y)
            key?.isDown = false
            key?.isSustain = false
            invalidate()
            return true
        }

        for (k in whiteKeys + blackKeys) {
            k.isDown = false
        }
        for (i in 0 until event.pointerCount) {
            val x = event.getX(i)
            val y = event.getY(i)

            val key = getDownKey(x, y)
            key?.isDown = isDown
        }
        for (k in whiteKeys + blackKeys) {
            if (!k.isDown) {
                k.isSustain = false
                continue
            }
            if (!k.isSustain) {
                pianoPlayer.play(k.note)
                k.isSustain = true
                onPlayListener?.onPlay(k.note)
            }
        }
        invalidate()
        return true
    }

    private fun getDownKey(x: Float, y: Float): Key? {
        // black keys are above of white keys
        for (k in blackKeys) {
            if (k.rect.contains(x, y)) return k
        }
        for (k in whiteKeys) {
            if (k.rect.contains(x, y)) return k
        }
        return null
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        pianoPlayer.onDestroy()
    }

    fun setOnLoadCompleteListener(onLoadComplete: () -> Unit) {
        pianoPlayer = PianoPlayer(context) {
            isTouchable = true
            onLoadComplete.invoke()
        }
    }

    fun setOnPlayListener(listener: OnPlayListener) {
        onPlayListener = listener
    }

    fun play(note: Int) {
        pianoPlayer.play(note)
    }

    interface OnPlayListener {
        fun onPlay(note: Int)
    }
}