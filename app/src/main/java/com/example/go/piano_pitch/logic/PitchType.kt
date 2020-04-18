package com.example.go.piano_pitch.logic

import com.example.go.piano_pitch.logic.usecase.*
import kotlin.reflect.KClass

@ExperimentalStdlibApi
enum class PitchType(
    private val className: KClass<*>,
    val title: String,
    val numberOfComponent: Int
) {
    C_TO_WHITE(CToWhite::class, "二音（ド→白鍵）", 1),
    C_TO_ANY(CToAny::class, "二音（ド→全て）", 1),
    WHITE_TO_WHITE(WhiteToWhite::class, "二音（白鍵→白鍵）", 1),
    ANY_TO_NEXT(AnyToNext::class, "二音（隣同士）", 1),
    ANY_TO_ANY(AnyToAny::class, "二音", 1),
    THREE_WHITE(ThreeWhite::class, "三音（白鍵）", 1),
    THREE(Three::class, "三音", 1),
    FIVE_WHITE(FiveWhite::class, "五音（白鍵）", 1),
    FIVE(Five::class, "五音", 1),
    ONE_DYAD_MAJOR_MINOR(OneDyadMajorMinor::class, "二和音（長・短）", 2),
    ONE_DYAD(OneDyad::class, "二和音", 2),
    TWO_DYAD(TwoDyad::class, "二つの二和音", 2),
    ;

    fun sample(): List<List<Int>> = (className.objectInstance as PitchTypeBase).sample()
}