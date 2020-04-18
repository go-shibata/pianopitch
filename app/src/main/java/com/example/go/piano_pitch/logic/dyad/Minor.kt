package com.example.go.piano_pitch.logic.dyad

enum class Minor(override val interval: Int) : Interval {
    SECOND(1),
    THIRD(3),
    SIXTH(8),
    SEVENTH(10)
}