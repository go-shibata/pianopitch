package com.example.go.piano_pitch.ui.pitch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.go.piano_pitch.R
import com.example.go.piano_pitch.data.Result
import com.example.go.piano_pitch.logic.usecase.CToOther
import javax.inject.Inject

class PitchViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    val results = arrayListOf<Result>()

    private val _questionCount = MutableLiveData(0)
    val questionCount: LiveData<Int> = _questionCount

    private val playedNotes = arrayListOf<String>()

    private val _playedNoteName = MutableLiveData<String>()
    val playedNoteName: LiveData<String> = _playedNoteName

    private val _question = MutableLiveData<List<String>>()
    val question: LiveData<List<String>> = _question

    private val _isStarted = MutableLiveData<Boolean>()
    val isStarted: LiveData<Boolean> = _isStarted

    private val _resultIsCorrect = MutableLiveData<Boolean>()
    val resultIsCorrect: LiveData<Boolean> = _resultIsCorrect

    private val _isFinish = MutableLiveData<Boolean>()
    val isFinish: LiveData<Boolean> = _isFinish

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
        val questionNotes = checkNotNull(question.value)
        val isCorrect = playedNotes.zip(questionNotes)
            .all { pair -> pair.first == pair.second }
        _resultIsCorrect.postValue(isCorrect)
        results.add(Result(isCorrect, playedNotes.toList(), questionNotes))

        if (questionCount.value == 10) {
            _isFinish.postValue(true)
        }
    }

    @ExperimentalStdlibApi
    fun fetchQuestion() {
        _questionCount.postValue(questionCount.value?.plus(1))
        playedNotes.clear()
        _question.postValue(null)
        _isStarted.postValue(true)
        _resultIsCorrect.postValue(null)
        val question = CToOther.sample()
        val noteNames = getApplication<Application>().resources.getStringArray(R.array.note_names)
        _question.postValue(question.map { noteNames[it] })
    }
}