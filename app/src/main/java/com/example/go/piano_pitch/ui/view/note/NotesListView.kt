package com.example.go.piano_pitch.ui.view.note

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import com.example.go.piano_pitch.R
import com.example.go.piano_pitch.data.Note
import kotlinx.android.synthetic.main.list_notes.view.*

class NotesListView(
    context: Context,
    notes: List<Note>
) : FrameLayout(context) {

    constructor(context: Context) : this(context, emptyList())

    init {
        val view = View.inflate(context, R.layout.list_notes, this)
        (view as FrameLayout).list.apply {
            notes.forEach {
                val textView = NoteTextView(context, it.name)
                addView(textView)
            }
        }
    }
}