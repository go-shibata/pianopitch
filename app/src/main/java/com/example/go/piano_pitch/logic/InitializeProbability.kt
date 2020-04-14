package com.example.go.piano_pitch.logic

import kotlin.math.abs

@ExperimentalStdlibApi
class InitializeProbability(
    private val mVector: Collection<Double>
) {

    companion object {
        private const val EPS: Double = 1E-6
    }

    private val mCumSumVector: List<Double>

    init {
        val sum = mVector.sum()
        if (abs(sum - 1f) > EPS) {
            throw IllegalArgumentException("The sum of vector must be 1, but given $sum")
        }

        mCumSumVector = mVector.scan(.0) { acc, value -> acc + value }.dropLast(1)
    }

    fun sample(): Int {
        val rand = Math.random()
        mCumSumVector.forEachIndexed { index, cumSum ->
            if (cumSum <= rand) return index
        }
        throw RuntimeException("Couldn't sample")
    }

    override fun toString(): String {
        val str = mVector.foldIndexed("") { index, acc, value ->
            val str = value.toString()
            return@foldIndexed acc + if (index == mVector.size - 1) str else "$str, "
        }
        return "[$str]"
    }
}