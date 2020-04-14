package com.example.go.piano_pitch.di.module

import com.example.go.piano_pitch.di.component.PianoFragmentSubcomponent
import com.example.go.piano_pitch.ui.piano.PianoFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [PianoFragmentSubcomponent::class])
interface PianoFragmentModule {

    @Binds
    @IntoMap
    @ClassKey(PianoFragment::class)
    fun bindPianoFragmentSubcomponentFactory(
        factory: PianoFragmentSubcomponent.Factory
    ): AndroidInjector.Factory<*>
}
