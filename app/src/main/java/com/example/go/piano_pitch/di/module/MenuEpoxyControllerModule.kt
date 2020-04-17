package com.example.go.piano_pitch.di.module

import com.example.go.piano_pitch.ui.menu.MenuEpoxyController
import com.example.go.piano_pitch.ui.menu.MenuFragment
import dagger.Binds
import dagger.Module

@Module
interface MenuEpoxyControllerModule {

    @Binds
    fun bindsOnClickPitchTypeListener(fragment: MenuFragment): MenuEpoxyController.OnClickPitchTypeListener
}