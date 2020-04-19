package com.example.go.piano_pitch.di.module

import android.app.Application
import com.example.go.piano_pitch.data.database.MyDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MyDatabaseModule {

    @Singleton
    @Provides
    fun provideMyDatabase(application: Application) = MyDatabase.getInstance(application)
}