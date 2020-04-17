package com.example.go.piano_pitch.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    val id: Long,
    val isCorrect: Boolean,
    val playedNotes: List<Note>,
    val questionNotes: List<Note>
) : Parcelable