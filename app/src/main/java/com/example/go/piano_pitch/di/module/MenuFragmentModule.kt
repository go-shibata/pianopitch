package com.example.go.piano_pitch.di.module

import com.example.go.piano_pitch.di.component.MenuFragmentSubcomponent
import com.example.go.piano_pitch.ui.menu.MenuFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [MenuFragmentSubcomponent::class])
interface MenuFragmentModule {

    @Binds
    @IntoMap
    @ClassKey(MenuFragment::class)
    fun bindMenuFragmentSubcomponentFactory(
        factory: MenuFragmentSubcomponent.Factory
    ): AndroidInjector.Factory<*>
}
