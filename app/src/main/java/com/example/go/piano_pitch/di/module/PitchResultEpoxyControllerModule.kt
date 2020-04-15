package com.example.go.piano_pitch.di.module

import com.example.go.piano_pitch.ui.pitch.PitchResultEpoxyController
import com.example.go.piano_pitch.ui.pitch.PitchResultFragment
import dagger.Binds
import dagger.Module

@Module
interface PitchResultEpoxyControllerModule {

    @Binds
    fun bindOnClickPlayButtonListener(fragment: PitchResultFragment): PitchResultEpoxyController.OnClickPlayButtonListener
}