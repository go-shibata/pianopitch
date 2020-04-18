package com.example.go.piano_pitch.logic.triad

class Triad(
    val root: Int,
    val interval: TriadInterval
) {

    fun getNotes() = listOf(root) + interval.getNotes(root)
}