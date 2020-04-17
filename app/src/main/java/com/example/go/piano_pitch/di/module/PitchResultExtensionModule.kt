package com.example.go.piano_pitch.di.module

import com.example.go.piano_pitch.ui.pitch.PitchResultEpoxyController
import com.example.go.piano_pitch.ui.pitch.PitchResultFragment
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface PitchResultExtensionModule {

    companion object {
        @Provides
        fun provideOnLoadComplete(fragment: PitchResultFragment) = fragment.onLoadComplete
    }

    @Binds
    fun bindOnClickPlayButtonListener(fragment: PitchResultFragment): PitchResultEpoxyController.OnClickPlayButtonListener
}