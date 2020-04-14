package com.example.go.piano_pitch.ui.pitch

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.go.piano_pitch.databinding.FragmentPitchBinding
import com.example.go.piano_pitch.di.ViewModelFactory
import com.example.go.piano_pitch.ui.view.piano.PianoView
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PitchFragment : Fragment(), PianoView.OnPlayListener {

    @Inject
    lateinit var factory: ViewModelFactory<PitchViewModel>
    private val viewModel: PitchViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPitchBinding.inflate(inflater, container, false).apply {
            viewModel = this@PitchFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
            piano.setOnPlayListener(this@PitchFragment)
        }
        return binding.root
    }

    override fun onPlay(note: Int) {
        viewModel.setPlayedNote(note)
    }
}
