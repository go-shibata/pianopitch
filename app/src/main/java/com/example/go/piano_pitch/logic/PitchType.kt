package com.example.go.piano_pitch.logic

import com.example.go.piano_pitch.logic.usecase.CToOther
import com.example.go.piano_pitch.logic.usecase.CToWhite
import com.example.go.piano_pitch.logic.usecase.PitchTypeBase
import kotlin.reflect.KClass

@ExperimentalStdlibApi
enum class PitchType(
    private val className: KClass<*>,
    val title: String
) {
    C_TO_WHITE(CToWhite::class, "二音（ド→白鍵）"),
    C_TO_OTHER(CToOther::class, "二音（ド→全て）");

    fun sample(): List<Int> = (className.objectInstance as PitchTypeBase).sample()
}