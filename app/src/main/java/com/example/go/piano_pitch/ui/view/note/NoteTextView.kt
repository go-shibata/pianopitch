package com.example.go.piano_pitch.ui.view.note

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import com.example.go.piano_pitch.R
import kotlinx.android.synthetic.main.text_note.view.*

class NoteTextView(
    context: Context,
    text: String
) : FrameLayout(context) {

    constructor(context: Context) : this(context, "")

    init {
        val view = View.inflate(context, R.layout.text_note, this)
        (view as FrameLayout).text.text = text
    }
}