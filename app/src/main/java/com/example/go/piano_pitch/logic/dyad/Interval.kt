package com.example.go.piano_pitch.logic.dyad

interface Interval {

    val interval: Int

    fun getNote(root: Int) = root + interval
}