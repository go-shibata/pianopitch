package com.example.go.piano_pitch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.go.piano_pitch.R
import com.example.go.piano_pitch.ui.piano.PianoFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PianoFragment.newInstance())
                .commitNow()
        }
    }
}
