package com.example.go.piano_pitch.di.module

import com.example.go.piano_pitch.di.component.PitchResultFragmentSubcomponent
import com.example.go.piano_pitch.ui.pitch.PitchResultFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [PitchResultFragmentSubcomponent::class])
interface PitchResultFragmentModule {

    @Binds
    @IntoMap
    @ClassKey(PitchResultFragment::class)
    fun bindPitchResultFragmentSubcomponentFactory(
        factory: PitchResultFragmentSubcomponent.Factory
    ): AndroidInjector.Factory<*>
}
