package com.example.go.piano_pitch.logic

import com.example.go.piano_pitch.logic.usecase.*
import kotlin.reflect.KClass

@ExperimentalStdlibApi
enum class PitchType(
    private val className: KClass<*>,
    val title: String
) {
    C_TO_WHITE(CToWhite::class, "二音（ド→白鍵）"),
    C_TO_ANY(CToAny::class, "二音（ド→全て）"),
    WHITE_TO_WHITE(WhiteToWhite::class, "二音（白鍵→白鍵）"),
    ANY_TO_NEXT(AnyToNext::class, "二音（隣同士）"),
    ANY_TO_ANY(AnyToAny::class, "二音"),
    ;

    fun sample(): List<Int> = (className.objectInstance as PitchTypeBase).sample()
}