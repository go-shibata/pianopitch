package com.example.go.piano_pitch.logic.dyad

enum class Major(override val interval: Int) : Interval {
    SECOND(2),
    THIRD(4),
    SIXTH(9),
    SEVENTH(11)
}