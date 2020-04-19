package com.example.go.piano_pitch.ui.view.piano

import android.app.Application
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.core.content.res.getResourceIdOrThrow
import com.example.go.piano_pitch.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PianoPlayer @Inject constructor(application: Application) {

    companion object {
        private const val START_NOTE_NUMBER = 60
    }

    private val pool: SoundPool
    private val noteToSound: Map<Int, Int>
    private val isLoaded: MutableMap<Int, Boolean>

    private var onLoadCompleteListener: OnLoadCompleteListener? = null

    init {
        val attr = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        pool = SoundPool.Builder()
            .setAudioAttributes(attr)
            .setMaxStreams(24)
            .build()

        val notesArray = application.resources.obtainTypedArray(R.array.notes)
        noteToSound = (0 until notesArray.length()).associate {
            START_NOTE_NUMBER + it to pool.load(application, notesArray.getResourceIdOrThrow(it), 1)
        }
        isLoaded = (0 until notesArray.length()).associate {
            checkNotNull(noteToSound[START_NOTE_NUMBER + it]) to false
        }.toMutableMap()
        notesArray.recycle()

        pool.setOnLoadCompleteListener { _, sampleId, status ->
            // if success
            if (status == 0) {
                isLoaded[sampleId] = true
            }
            if (isLoaded.all { it.value }) {
                onLoadCompleteListener?.onLoadComplete()
            }
        }
    }

    fun play(note: Int) {
        val sound = noteToSound[note] ?: return
        pool.play(sound, 1f, 1f, 0, 0, 1f)
    }

    fun onDestroy() {
        pool.release()
    }

    fun setOnLoadCompleteListener(listener: OnLoadCompleteListener) {
        onLoadCompleteListener = listener
    }

    interface OnLoadCompleteListener {
        fun onLoadComplete()
    }
}