package com.example.go.piano_pitch.ui.pitch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.go.piano_pitch.R
import com.example.go.piano_pitch.data.Result
import com.example.go.piano_pitch.ui.view.piano.PianoPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class PitchResultViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val pianoPlayer = PianoPlayer(application)

    fun play(result: Result) {
        CoroutineScope(Dispatchers.Default).launch {
            result.questionNotes.forEach {
                delay(1000)
                val index = getApplication<Application>().resources
                    .getStringArray(R.array.note_names)
                    .indexOf(it)
                pianoPlayer.play(60 + index)
            }
        }
    }
}