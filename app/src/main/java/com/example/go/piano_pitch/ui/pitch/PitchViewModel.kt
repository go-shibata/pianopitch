package com.example.go.piano_pitch.ui.pitch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.go.piano_pitch.R
import com.example.go.piano_pitch.logic.usecase.CToOther
import javax.inject.Inject

class PitchViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private var playedNotes = arrayListOf<String>()

    private val _playedNoteName = MutableLiveData<String>()
    val playedNoteName: LiveData<String> = _playedNoteName

    private val _question = MutableLiveData<List<String>>()
    val question: LiveData<List<String>> = _question

    private val _isStarted = MutableLiveData<Boolean>()
    val isStarted: LiveData<Boolean> = _isStarted

    private val _resultIsCorrect = MutableLiveData<Boolean>()
    val resultIsCorrect: LiveData<Boolean> = _resultIsCorrect

    fun setPlayedNote(note: Int) {
        val noteName = getApplication<Application>().resources
            .getStringArray(R.array.note_names)[note % 12]
        _playedNoteName.postValue(noteName)

        playedNotes.add(noteName)
        if (playedNotes.size == question.value?.size) {
            checkAnswer()
        }
    }

    private fun checkAnswer() {
        val isCorrect = playedNotes.zip(checkNotNull(question.value))
            .all { pair -> pair.first == pair.second }
        _resultIsCorrect.postValue(isCorrect)
    }

    @ExperimentalStdlibApi
    fun fetchQuestion() {
        _isStarted.postValue(true)
        val question = CToOther.sample()
        val noteNames = getApplication<Application>().resources.getStringArray(R.array.note_names)
        _question.postValue(question.map { noteNames[it] })
    }
}