package com.example.go.piano_pitch.logic

import com.example.go.piano_pitch.logic.usecase.CToOther
import com.example.go.piano_pitch.logic.usecase.PitchTypeBase
import kotlin.reflect.KClass

@ExperimentalStdlibApi
enum class PitchType(
    private val className: KClass<*>
) {
    C_TO_OTHER(CToOther::class);

    fun sample(): List<Int> = (className.objectInstance as PitchTypeBase).sample()
}