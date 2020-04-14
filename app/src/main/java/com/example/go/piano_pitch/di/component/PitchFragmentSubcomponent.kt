package com.example.go.piano_pitch.di.component

import com.example.go.piano_pitch.ui.pitch.PitchFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface PitchFragmentSubcomponent : AndroidInjector<PitchFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<PitchFragment>
}