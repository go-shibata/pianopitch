package com.example.go.piano_pitch.di.component

import com.example.go.piano_pitch.ui.menu.MenuFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface MenuFragmentSubcomponent : AndroidInjector<MenuFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MenuFragment>
}