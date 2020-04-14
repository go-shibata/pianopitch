package com.example.go.piano_pitch.di.component

import com.example.go.piano_pitch.ui.piano.PianoFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface PianoFragmentSubcomponent : AndroidInjector<PianoFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<PianoFragment>
}