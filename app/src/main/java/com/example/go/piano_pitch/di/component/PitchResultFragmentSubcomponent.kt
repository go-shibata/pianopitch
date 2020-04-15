package com.example.go.piano_pitch.di.component

import com.example.go.piano_pitch.di.module.PitchResultEpoxyControllerModule
import com.example.go.piano_pitch.ui.pitch.PitchResultFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [PitchResultEpoxyControllerModule::class])
interface PitchResultFragmentSubcomponent : AndroidInjector<PitchResultFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<PitchResultFragment>
}