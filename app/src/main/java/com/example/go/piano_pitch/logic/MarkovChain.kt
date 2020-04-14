package com.example.go.piano_pitch.logic

@ExperimentalStdlibApi
class MarkovChain constructor(
    private val initialize: InitializeProbability,
    private val transition: TransitionProbability
) {

    fun sample(size: Int): List<Int> {
        val array = Array(size) { 0 }
        array[0] = initialize.sample()
        for (i in 1 until size) {
            array[i] = transition.sample(array[i - 1])
        }
        return array.toList()
    }
}