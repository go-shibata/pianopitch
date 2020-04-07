package com.example.go.pianoroll.ui.piano

import android.graphics.RectF

data class Key(
    val sound: Int,
    val rect: RectF,
    var isDown: Boolean = false
)