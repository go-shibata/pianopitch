package com.example.go.piano_pitch.ui.view.piano

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.core.content.res.getResourceIdOrThrow
import com.example.go.piano_pitch.R

class PianoPlayer(
    context: Context
) {

    companion object {
        private const val START_NOTE_NUMBER = 60
    }

    private val pool: SoundPool
    private val noteToSound: Map<Int, Int>

    init {
        val attr = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        pool = SoundPool.Builder()
            .setAudioAttributes(attr)
            .setMaxStreams(24)
            .build()

        val notesArray = context.resources.obtainTypedArray(R.array.notes)
        noteToSound = (0 until notesArray.length()).associate {
            START_NOTE_NUMBER + it to pool.load(context, notesArray.getResourceIdOrThrow(it), 1)
        }
        notesArray.recycle()
    }

    fun play(note: Int) {
        val sound = noteToSound[note] ?: return
        pool.play(sound, 1f, 1f, 0, 0, 1f)
    }

    fun onDestroy() {
        pool.release()
    }
}