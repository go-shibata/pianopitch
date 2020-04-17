package com.example.go.piano_pitch.logic.usecase

import com.example.go.piano_pitch.logic.MarkovChain

abstract class PitchTypeBase {

    @ExperimentalStdlibApi
    protected abstract val markovChain: MarkovChain

    abstract fun sample(): List<Int>
}