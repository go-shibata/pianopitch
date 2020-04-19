package com.example.go.piano_pitch.logic

import com.example.go.piano_pitch.logic.usecase.*
import kotlin.reflect.KClass

@ExperimentalStdlibApi
enum class PitchType(
    private val className: KClass<*>,
    val id: Long,
    val title: String,
    val numberOfComponent: Int
) {
    C_TO_WHITE(CToWhite::class, 0, "二音（ド→白鍵）", 1),
    C_TO_ANY(CToAny::class, 1, "二音（ド→全て）", 1),
    WHITE_TO_WHITE(WhiteToWhite::class, 2, "二音（白鍵→白鍵）", 1),
    ANY_TO_NEXT(AnyToNext::class, 3, "二音（隣同士）", 1),
    ANY_TO_ANY(AnyToAny::class, 4, "二音", 1),
    THREE_WHITE(ThreeWhite::class, 5, "三音（白鍵）", 1),
    THREE(Three::class, 6, "三音", 1),
    FIVE_WHITE(FiveWhite::class, 7, "五音（白鍵）", 1),
    FIVE(Five::class, 8, "五音", 1),
    ONE_DYAD_MAJOR_MINOR(OneDyadMajorMinor::class, 1000, "二和音（長・短）", 2),
    ONE_DYAD(OneDyad::class, 1001, "二和音", 2),
    TWO_DYAD(TwoDyad::class, 1002, "二つの二和音", 2),
    ONE_TRIAD_MAJOR(OneTriadMajor::class, 2000, "三和音（長）", 3),
    ONE_TRIAD(OneTriad::class, 2001, "三和音", 3),
    TWO_TRIAD(TwoTriad::class, 2002, "二つの三和音", 3),
    ONE_TETRAD(OneTetrad::class, 3001, "四和音", 4),
    TWO_TETRAD(TwoTetrad::class, 3002, "二つの四和音", 4),
    ;

    fun sample(): List<List<Int>> = (className.objectInstance as PitchTypeBase).sample()
}