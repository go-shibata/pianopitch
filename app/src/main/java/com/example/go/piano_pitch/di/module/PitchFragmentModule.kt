package com.example.go.piano_pitch.di.module

import com.example.go.piano_pitch.di.component.PitchFragmentSubcomponent
import com.example.go.piano_pitch.ui.pitch.PitchFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [PitchFragmentSubcomponent::class])
interface PitchFragmentModule {

    @Binds
    @IntoMap
    @ClassKey(PitchFragment::class)
    fun bindPitchFragmentSubcomponentFactory(
        factory: PitchFragmentSubcomponent.Factory
    ): AndroidInjector.Factory<*>
}
