package com.example.go.piano_pitch.logic.usecase

import com.example.go.piano_pitch.logic.InitializeProbability
import com.example.go.piano_pitch.logic.MarkovChain
import com.example.go.piano_pitch.logic.TransitionProbability
import kotlin.math.abs

@ExperimentalStdlibApi
object AnyToNext : PitchTypeBase() {

    override val markovChain: MarkovChain

    init {
        val init = List(12) { 1.0 / 12 }

        val trans = List(12) { from ->
            List(12) { to ->
                if (abs(to - from) in listOf(1, 11)) 0.5 else 0.0
            }
        }

        markovChain = MarkovChain(InitializeProbability(init), TransitionProbability(trans))
    }

    override fun sample(): List<List<Int>> = markovChain.sample(2).asNoteList()
}