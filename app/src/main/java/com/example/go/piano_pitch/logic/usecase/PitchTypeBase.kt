package com.example.go.piano_pitch.logic.usecase

import com.example.go.piano_pitch.logic.MarkovChain

abstract class PitchTypeBase {

    companion object {
        fun List<Int>.asNoteList(): List<List<Int>> = this.map { listOf(it) }
    }

    @ExperimentalStdlibApi
    protected abstract val markovChain: MarkovChain

    abstract fun sample(): List<List<Int>>
}