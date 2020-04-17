package com.example.go.piano_pitch.ui.piano

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.go.piano_pitch.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_piano.view.*

class PianoFragment : Fragment() {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_piano, container, false).apply {
            // TODO: pianoPlayer, pianoView の関係を直す
            piano.setOnLoadCompleteListener {}
        }
    }
}
