package com.example.go.piano_pitch.ui.pitch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.go.piano_pitch.R
import javax.inject.Inject

class PitchViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val _playedNoteName = MutableLiveData<String>()
    val playedNoteName: LiveData<String> = _playedNoteName

    fun setPlayedNote(note: Int) {
        val noteName = getApplication<Application>().resources
            .getStringArray(R.array.note_names)[note % 12]
        _playedNoteName.postValue(noteName)
    }
}