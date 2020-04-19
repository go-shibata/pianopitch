package com.example.go.piano_pitch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.go.piano_pitch.ui.view.piano.PianoPlayer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainActivityViewModel @Inject constructor(
    val pianoPlayer: PianoPlayer
) : ViewModel(), PianoPlayer.OnLoadCompleteListener {

    private val _onLoadComplete = MutableLiveData<Unit>()
    val onLoadComplete: LiveData<Unit> = _onLoadComplete

    init {
        pianoPlayer.setOnLoadCompleteListener(this)
    }

    override fun onLoadComplete() {
        _onLoadComplete.postValue(Unit)
    }

    override fun onCleared() {
        pianoPlayer.onDestroy()
        super.onCleared()
    }
}