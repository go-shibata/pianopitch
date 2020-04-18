package com.example.go.piano_pitch.logic.usecase

import com.example.go.piano_pitch.logic.InitializeProbability
import com.example.go.piano_pitch.logic.MarkovChain
import com.example.go.piano_pitch.logic.TransitionProbability
import com.example.go.piano_pitch.logic.dyad.All
import com.example.go.piano_pitch.logic.dyad.Dyad

@ExperimentalStdlibApi
object OneDyad : PitchTypeBase() {

    override val markovChain: MarkovChain

    val all = All.values()

    init {
        val size = 12 * all.size
        val init = List(size) { 1.0 / size }

        val trans = List(size) {
            List(size) { 1.0 / size }
        }

        markovChain = MarkovChain(InitializeProbability(init), TransitionProbability(trans))
    }

    override fun sample(): List<List<Int>> = markovChain.sample(1).asNoteList(12, all.size).map {
        Dyad(it[0], all[it[1]]).getNotes()
    }
}
