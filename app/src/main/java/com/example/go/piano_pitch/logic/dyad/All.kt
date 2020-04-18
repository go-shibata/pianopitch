package com.example.go.piano_pitch.logic.dyad

object All {

    fun values() = listOf<List<Interval>>(
        Major.values().toList(),
        Minor.values().toList(),
        Perfect.values().toList(),
        Diminished.values().toList()
    ).flatten()
}