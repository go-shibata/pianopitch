package com.example.go.piano_pitch.logic

import com.example.go.piano_pitch.logic.usecase.AnyToAny
import com.example.go.piano_pitch.logic.usecase.CToAny
import com.example.go.piano_pitch.logic.usecase.CToWhite
import com.example.go.piano_pitch.logic.usecase.PitchTypeBase
import kotlin.reflect.KClass

@ExperimentalStdlibApi
enum class PitchType(
    private val className: KClass<*>,
    val title: String
) {
    C_TO_WHITE(CToWhite::class, "二音（ド→白鍵）"),
    C_TO_ANY(CToAny::class, "二音（ド→全て）"),
    ANY_TO_ANY(AnyToAny::class, "二音"),
    ;

    fun sample(): List<Int> = (className.objectInstance as PitchTypeBase).sample()
}