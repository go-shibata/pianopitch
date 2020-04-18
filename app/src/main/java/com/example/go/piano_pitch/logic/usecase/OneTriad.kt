package com.example.go.piano_pitch.logic.usecase

import com.example.go.piano_pitch.logic.InitializeProbability
import com.example.go.piano_pitch.logic.MarkovChain
import com.example.go.piano_pitch.logic.TransitionProbability
import com.example.go.piano_pitch.logic.triad.Triad
import com.example.go.piano_pitch.logic.triad.TriadInterval

@ExperimentalStdlibApi
object OneTriad : PitchTypeBase() {

    override val markovChain: MarkovChain

    val all = TriadInterval.values()

    init {
        val size = 12 * all.size
        val init = List(size) { 1.0 / size }

        val trans = List(size) {
            List(size) { 1.0 / size }
        }

        markovChain = MarkovChain(InitializeProbability(init), TransitionProbability(trans))
    }

    override fun sample(): List<List<Int>> = markovChain.sample(1).asIndexList(12, all.size).map {
        Triad(it[0], all[it[1]]).getNotes()
    }
}
