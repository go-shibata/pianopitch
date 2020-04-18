package com.example.go.piano_pitch.logic.tetrad

class Tetrad(
    val root: Int,
    val interval: TetradInterval
) {

    fun getNotes() = listOf(root) + interval.getNotes(root)
}