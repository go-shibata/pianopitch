package com.example.go.piano_pitch.logic.usecase

import com.example.go.piano_pitch.logic.InitializeProbability
import com.example.go.piano_pitch.logic.MarkovChain
import com.example.go.piano_pitch.logic.TransitionProbability

@ExperimentalStdlibApi
object CToAny : PitchTypeBase() {

    override val markovChain: MarkovChain

    init {
        val init = List(12) {
            if (it == 0) 1.0 else 0.0
        }

        val trans = List(12) {
            List(12) { 1.0 / 12 }
        }

        markovChain = MarkovChain(InitializeProbability(init), TransitionProbability(trans))
    }

    override fun sample(): List<List<Int>> = markovChain.sample(2).asIndexList()
}