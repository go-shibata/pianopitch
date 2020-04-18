package com.example.go.piano_pitch.logic.dyad

enum class Perfect(override val interval: Int) : Interval {
    FOURTH(5),
    FIFTH(7),
    EIGHTH(12)
}