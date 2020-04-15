package com.example.go.piano_pitch.di.component

import android.app.Application
import com.example.go.piano_pitch.App
import com.example.go.piano_pitch.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainActivityModule::class,
        MenuFragmentModule::class,
        PianoFragmentModule::class,
        PitchFragmentModule::class,
        PitchResultFragmentModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun inject(app: App)
}