package com.example.go.piano_pitch.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Note(
    val note: Int,
    val name: String
) : Parcelable {

    companion object {
        private val names = listOf(
            "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"
        )

        fun fromIndex(index: Int): Note = Note(index + 60, names[index % 12])
    }

    constructor(note: Int) : this(note, names[note % 12])
}