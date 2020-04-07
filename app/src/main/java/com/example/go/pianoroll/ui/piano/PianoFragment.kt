package com.example.go.pianoroll.ui.piano

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.go.pianoroll.R

class PianoFragment : Fragment() {

    companion object {
        fun newInstance() = PianoFragment()
    }

    private lateinit var viewModel: PianoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.piano_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PianoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
