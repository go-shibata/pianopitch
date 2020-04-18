package com.example.go.piano_pitch.logic.usecase

import com.example.go.piano_pitch.logic.InitializeProbability
import com.example.go.piano_pitch.logic.MarkovChain
import com.example.go.piano_pitch.logic.TransitionProbability

@ExperimentalStdlibApi
object Five : PitchTypeBase() {

    override val markovChain: MarkovChain

    init {
        val init = List(12) { 1.0 / 12 }

        val trans = List(12) {
            List(12) { 1.0 / 12 }
        }

        markovChain = MarkovChain(InitializeProbability(init), TransitionProbability(trans))
    }

    override fun sample(): List<List<Int>> = markovChain.sample(5).asIndexList()
}
