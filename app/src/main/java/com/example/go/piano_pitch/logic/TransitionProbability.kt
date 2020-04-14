package com.example.go.piano_pitch.logic

import kotlin.math.abs

@ExperimentalStdlibApi
class TransitionProbability(
    private val mMatrix: Collection<Collection<Double>>
) {

    companion object {
        private const val EPS: Double = 1E-6
    }

    private val mCumSumMatrix: List<List<Double>>

    init {
        for (row in mMatrix) {
            val sum = row.sum()
            if (abs(sum - 1f) > EPS) {
                throw IllegalArgumentException("The sum of each row must be 1, but given $sum")
            }
        }

        mCumSumMatrix = mMatrix.map { row ->
            row.scan(.0) { acc, value -> acc + value }.drop(1)
        }
    }

    fun sample(before: Int): Int {
        val cumSumRow = mCumSumMatrix[before]
        val rand = Math.random()
        cumSumRow.forEachIndexed { index, cumSum ->
            if (rand < cumSum) return index
        }
        throw RuntimeException("Couldn't sample")
    }

    override fun toString(): String {
        val str = mMatrix.foldIndexed("") { indexRow, accRow, row ->
            var rowStr = row.foldIndexed("") row@{ index, acc, value ->
                val str = value.toString()
                return@row acc + if (index == row.size - 1) str else "$str, "
            }
            rowStr = if (indexRow == mMatrix.size - 1) "[$rowStr]" else "[$rowStr],\n"
            val indent = if (indexRow != 0) " " else ""
            return@foldIndexed accRow + indent + rowStr
        }
        return "[$str]"
    }
}