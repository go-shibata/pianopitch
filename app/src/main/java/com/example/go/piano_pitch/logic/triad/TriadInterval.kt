package com.example.go.piano_pitch.logic.triad

import com.example.go.piano_pitch.logic.dyad.*

enum class TriadInterval(
    private vararg val interval: Interval
) {
    MAJOR(Major.THIRD, Perfect.FIFTH),
    MINOR(Minor.THIRD, Perfect.FIFTH),
    AUGMENTED(Major.THIRD, Minor.SIXTH),
    DIMINISHED(Minor.THIRD, Diminished.FIFTH),
    SUSPENDED_SECOND(Major.SECOND, Perfect.FIFTH),
    SUSPENDED_FOURTH(Perfect.FOURTH, Perfect.FIFTH),
    ;

    fun getNotes(root: Int) = interval.map { it.getNote(root) }
}