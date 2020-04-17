package com.example.go.piano_pitch.ui.pitch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.go.piano_pitch.data.Result
import com.example.go.piano_pitch.ui.view.piano.PianoPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class PitchResultViewModel @Inject constructor(
    application: Application,
    onLoadComplete: () -> Unit
) : AndroidViewModel(application) {

    private val pianoPlayer = PianoPlayer(application) { onLoadComplete.invoke() }

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

    override fun onCleared() {
        pianoPlayer.onDestroy()
        super.onCleared()
    }
}