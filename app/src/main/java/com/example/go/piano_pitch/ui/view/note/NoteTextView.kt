package com.example.go.piano_pitch.ui.view.note

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.go.piano_pitch.R
import kotlinx.android.synthetic.main.text_note.view.*

class NoteTextView(
    context: Context,
    text: String
) : FrameLayout(context) {

    constructor(context: Context) : this(context, "")

    init {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.text_note, this, true)

        (view as FrameLayout).text.text = text
    }
}