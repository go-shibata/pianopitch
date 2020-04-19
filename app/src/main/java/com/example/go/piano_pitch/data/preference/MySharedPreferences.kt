package com.example.go.piano_pitch.data.preference

import android.app.Application
import android.content.SharedPreferences
import com.example.go.piano_pitch.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MySharedPreferences @Inject constructor(
    private val application: Application,
    private val sharedPreferences: SharedPreferences
) {

    fun getCanListenAgain(): Boolean =
        sharedPreferences.getBoolean(application.getString(R.string.key_can_listen_again), false)
}