package com.example.go.piano_pitch.logic.dyad

class Dyad(
    private val root: Int,
    private val interval: Interval
) {

    fun getNotes() = listOf(root, interval.getNote(root))
}