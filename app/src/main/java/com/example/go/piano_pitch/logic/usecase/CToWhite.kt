package com.example.go.piano_pitch.logic.usecase

import com.example.go.piano_pitch.logic.InitializeProbability
import com.example.go.piano_pitch.logic.MarkovChain
import com.example.go.piano_pitch.logic.TransitionProbability

@ExperimentalStdlibApi
object CToWhite : PitchTypeBase() {

    override val markovChain: MarkovChain

    init {
        val init = List(12) {
            if (it == 0) 1.0 else 0.0
        }

        val trans = List(12) {
            List(12) {
                if (it in listOf(1, 3, 6, 8, 10)) 0.0 else 1.0 / 7
            }
        }

        markovChain = MarkovChain(InitializeProbability(init), TransitionProbability(trans))
    }

    override fun sample(): List<List<Int>> = markovChain.sample(2).asIndexList()
}