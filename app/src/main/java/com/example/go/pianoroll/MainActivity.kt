package com.example.go.pianoroll

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.go.pianoroll.ui.piano.PianoFragment

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
