package com.example.go.pianoroll.ui.piano

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

    private val pianoPlayer = PianoPlayer(context)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        keyWidth = w.toFloat() / N_WHITE_KEY
        keyHeight = h.toFloat()
        var noteNumber = START_NOTE_NUMBER

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

            left = (i + 0.75f) * keyWidth
            right = left + keyWidth * 0.5f
            val height = keyHeight * 0.6f
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
        checkNotNull(event)
        val action = event.action
        val isDown = action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE

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
}