package com.example.go.pianoroll.ui.piano

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes
import androidx.core.content.res.getResourceIdOrThrow
import com.example.go.pianoroll.R

class PianoPlayer(
    context: Context
) {

    companion object {
        private const val START_NOTE_NUMBER = 60
    }

    private val player = MediaPlayerPool(context, 24)
    private val notes: Map<Int, Int>

    init {
        val notesArray = context.resources.obtainTypedArray(R.array.notes)
        notes = (0 until notesArray.length()).associate {
            START_NOTE_NUMBER + it to notesArray.getResourceIdOrThrow(it)
        }
        notesArray.recycle()
    }

    fun play(note: Int) {
        val noteRes = notes[note] ?: return
        player.playSound(noteRes)
    }

    fun onDestroy() {
        player.onDestroy()
    }

    class MediaPlayerPool(context: Context, maxStreams: Int) {
        private val context: Context = context.applicationContext

        private val mediaPlayerPool = (0 until maxStreams).map { buildPlayer() }.toMutableList()
        private val playersInUse = mutableListOf<MediaPlayer>()

        private fun buildPlayer() = MediaPlayer().apply {
            setOnPreparedListener { start() }
            setOnCompletionListener { recyclePlayer(it) }
        }

        /**
         * Returns a [MediaPlayer] if one is available,
         * otherwise null.
         */
        private fun requestPlayer(): MediaPlayer? {
            return if (mediaPlayerPool.isNotEmpty()) {
                mediaPlayerPool.removeAt(0).also {
                    playersInUse += it
                }
            } else null
        }

        private fun recyclePlayer(mediaPlayer: MediaPlayer) {
            mediaPlayer.reset()
            playersInUse -= mediaPlayer
            mediaPlayerPool += mediaPlayer
        }

        fun playSound(@RawRes rawResId: Int) {
            val assetFileDescriptor = context.resources.openRawResourceFd(rawResId) ?: return
            val mediaPlayer = requestPlayer() ?: return

            mediaPlayer.run {
                setDataSource(
                    assetFileDescriptor.fileDescriptor, assetFileDescriptor.startOffset,
                    assetFileDescriptor.declaredLength
                )
                prepareAsync()
            }
        }

        fun onDestroy() {
            mediaPlayerPool.forEach { it.release() }
            playersInUse.forEach { it.release() }
        }
    }
}