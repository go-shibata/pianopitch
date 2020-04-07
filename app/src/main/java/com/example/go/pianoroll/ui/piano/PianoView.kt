package com.example.go.pianoroll.ui.piano

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class PianoView(
    context: Context,
    attrs: AttributeSet
) : View(context, attrs) {

    companion object {
        private const val N_WHITE_KEY = 14
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

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        keyWidth = w.toFloat() / N_WHITE_KEY
        keyHeight = h.toFloat()
        var count = 15

        for (i in 0 until N_WHITE_KEY) {
            val left = i * keyWidth
            val right = if (i != N_WHITE_KEY) left + keyWidth else w.toFloat()
            whiteKeys.add(
                Key(
                    i,
                    RectF(left, 0f, right, keyHeight)
                )
            )
        }

        for (i in 0 until N_WHITE_KEY) {
            if (i % 7 in listOf(2, 6)) continue

            val left = (i + 0.75f) * keyWidth
            val right = left + keyWidth * 0.5f
            val height = keyHeight * 0.6f
            blackKeys.add(
                Key(
                    count++,
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
}