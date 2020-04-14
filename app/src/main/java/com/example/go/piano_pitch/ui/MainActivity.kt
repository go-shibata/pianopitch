package com.example.go.piano_pitch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.go.piano_pitch.R
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}
