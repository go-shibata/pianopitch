package com.example.go.pianoroll.ui.piano

import android.content.Context
import android.media.MediaPlayer
import androidx.core.content.res.getResourceIdOrThrow
import com.example.go.pianoroll.R

class PianoPlayer(
    context: Context
) {

    companion object {
        private const val START_NOTE_NUMBER = 60
    }

    private val players: Map<Int, MediaPlayer>

    init {
        val notes = context.resources.obtainTypedArray(R.array.notes)
        players = (0 until notes.length()).associate {
            START_NOTE_NUMBER + it to MediaPlayer.create(context, notes.getResourceIdOrThrow(it))
        }
        notes.recycle()
    }

    fun play(note: Int) {
        players[note]?.run {
            seekTo(0)
            start()
        }
    }

    fun stop(note: Int) {
        players[note]?.run {
            pause()
        }
    }

    fun isPlaying(note: Int): Boolean {
        return players[note]?.isPlaying ?: false
    }

    fun onDestroy() {
        players.forEach {
            it.value.release()
        }
    }
}