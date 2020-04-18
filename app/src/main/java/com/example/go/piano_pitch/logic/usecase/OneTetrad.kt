package com.example.go.piano_pitch.logic.usecase

import com.example.go.piano_pitch.logic.InitializeProbability
import com.example.go.piano_pitch.logic.MarkovChain
import com.example.go.piano_pitch.logic.TransitionProbability
import com.example.go.piano_pitch.logic.tetrad.Tetrad
import com.example.go.piano_pitch.logic.tetrad.TetradInterval

@ExperimentalStdlibApi
object OneTetrad : PitchTypeBase() {

    override val markovChain: MarkovChain

    val all = TetradInterval.values()

    init {
        val size = 12 * all.size
        val init = List(size) { 1.0 / size }

        val trans = List(size) {
            List(size) { 1.0 / size }
        }

        markovChain = MarkovChain(InitializeProbability(init), TransitionProbability(trans))
    }

    override fun sample(): List<List<Int>> = markovChain.sample(1).asIndexList(12, all.size).map {
        Tetrad(it[0], all[it[1]]).getNotes()
    }
}
