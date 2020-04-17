package com.example.go.piano_pitch.ui.view.note

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.go.piano_pitch.R
import kotlinx.android.synthetic.main.text_note.view.*

class NoteTextView(
    context: Context,
    attrs: AttributeSet?,
    text: String
) : FrameLayout(context, attrs) {

    constructor(context: Context) : this(context, null, "")

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, "")

    constructor(context: Context, text: String) : this(context, null, text)

    init {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.text_note, this, true)

        (view as FrameLayout).text.text = text
    }
}