package com.example.go.piano_pitch.logic.usecase

import com.example.go.piano_pitch.logic.MarkovChain

abstract class PitchTypeBase {

    companion object {
        fun List<Int>.asNoteList(vararg size: Int = IntArray(1) { 1 }): List<List<Int>> = this.map {
            var index = it
            size.reversed().map { s ->
                val value = index % s
                index /= s
                value
            }.reversed()
        }
    }

    @ExperimentalStdlibApi
    protected abstract val markovChain: MarkovChain

    abstract fun sample(): List<List<Int>>
}