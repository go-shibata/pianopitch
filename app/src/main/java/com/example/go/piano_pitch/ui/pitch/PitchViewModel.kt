package com.example.go.piano_pitch.ui.pitch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.go.piano_pitch.data.Note
import com.example.go.piano_pitch.data.Result
import com.example.go.piano_pitch.logic.PitchType
import javax.inject.Inject
import kotlin.random.Random

class PitchViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    @ExperimentalStdlibApi
    lateinit var pitchType: PitchType

    val results = arrayListOf<Result>()

    private val _questionCount = MutableLiveData(0)
    val questionCount: LiveData<Int> = _questionCount

    private val bufferPlayedNotes = arrayListOf<Note>()
    private val playedNotes = arrayListOf<List<Note>>()

    private val _playedNote = MutableLiveData<List<Note>>()
    val playedNote: LiveData<List<Note>> = _playedNote

    private val _question = MutableLiveData<List<List<Note>>>()
    val question: LiveData<List<List<Note>>> = _question

    private val _isStarted = MutableLiveData<Boolean>()
    val isStarted: LiveData<Boolean> = _isStarted

    private val _resultIsCorrect = MutableLiveData<Boolean>()
    val resultIsCorrect: LiveData<Boolean> = _resultIsCorrect

    private val _isFinish = MutableLiveData<Boolean>()
    val isFinish: LiveData<Boolean> = _isFinish

    @ExperimentalStdlibApi
    fun setPlayedNote(note: Int) {
        if (isStarted.value != true || resultIsCorrect.value != null) return

        val data = Note(note)

        bufferPlayedNotes.add(data)
        if (bufferPlayedNotes.size == pitchType.numberOfComponent) {
            val list = bufferPlayedNotes.toList()
            playedNotes.add(list)
            _playedNote.postValue(list)
            bufferPlayedNotes.clear()
        }
        if (playedNotes.size == question.value?.size) {
            checkAnswer()
        }
    }

    private fun checkAnswer() {
        val questionNotes = checkNotNull(question.value)
        val isCorrect = playedNotes.zip(questionNotes)
            .all { pair ->
                pair.first.zip(pair.second)
                    .all { p -> p.first == p.second }
            }
        _resultIsCorrect.postValue(isCorrect)
        results.add(
            Result(
                Random.nextLong(),
                isCorrect,
                playedNotes.toList(),
                questionNotes
            )
        )

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
        val question = pitchType.sample()
        _question.postValue(question.map { it.map { note -> Note.fromIndex(note) } })
    }
}