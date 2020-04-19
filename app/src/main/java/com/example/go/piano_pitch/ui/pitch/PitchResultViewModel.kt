package com.example.go.piano_pitch.ui.pitch

import androidx.lifecycle.ViewModel
import com.example.go.piano_pitch.data.Result
import com.example.go.piano_pitch.ui.view.piano.PianoPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class PitchResultViewModel @Inject constructor(private val pianoPlayer: PianoPlayer) : ViewModel() {

    fun play(result: Result) {
        CoroutineScope(Dispatchers.Default).launch {
            result.questionNotes.forEach { list ->
                delay(1000)
                list.forEach {
                    pianoPlayer.play(it.note)
                }
            }
        }
    }
}