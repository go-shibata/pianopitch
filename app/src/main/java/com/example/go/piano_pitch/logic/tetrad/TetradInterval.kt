package com.example.go.piano_pitch.logic.tetrad

import com.example.go.piano_pitch.logic.dyad.*

enum class TetradInterval(
    private vararg val interval: Interval
) {
    MAJOR_SEVENTH(Major.THIRD, Perfect.FIFTH, Major.SEVENTH),
    DOMINANT_SEVENTH(Major.THIRD, Perfect.FIFTH, Minor.SEVENTH),
    DIMINISHED_SEVENTH(Major.THIRD, Perfect.FIFTH, Major.SIXTH),
    MINOR_SEVENTH(Minor.THIRD, Perfect.FIFTH, Minor.SEVENTH),
    MINOR_MAJOR_SEVENTH(Minor.THIRD, Perfect.FIFTH, Major.SEVENTH),
    MINOR_SEVENTH_FLATTED_FIFTH(Minor.THIRD, Diminished.FIFTH, Minor.SEVENTH),
    MAJOR_SIXTH(Major.THIRD, Perfect.FIFTH, Major.SIXTH),
    MINOR_SIXTH(Minor.THIRD, Perfect.FIFTH, Minor.SIXTH),
    ;

    fun getNotes(root: Int) = interval.map { it.getNote(root) }
}