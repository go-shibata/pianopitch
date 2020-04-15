package com.example.go.piano_pitch.ui.pitch

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.go.piano_pitch.R
import com.example.go.piano_pitch.databinding.FragmentPitchBinding
import com.example.go.piano_pitch.di.ViewModelFactory
import com.example.go.piano_pitch.ui.view.piano.PianoView
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PitchFragment : Fragment(), PianoView.OnPlayListener {

    @Inject
    lateinit var factory: ViewModelFactory<PitchViewModel>
    private val viewModel: PitchViewModel by viewModels { factory }

    private lateinit var binding: FragmentPitchBinding

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPitchBinding.inflate(inflater, container, false).apply {
            viewModel = this@PitchFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
            piano.setOnPlayListener(this@PitchFragment)
        }
        return binding.root
    }

    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.playedNoteName.observe(viewLifecycleOwner, Observer {
            val layoutParams = LinearLayout.LayoutParams(
                resources.getDimensionPixelSize(R.dimen.pitch_notes_size),
                resources.getDimensionPixelSize(R.dimen.pitch_notes_size)
            )
            val textView = TextView(requireContext()).apply {
                setLayoutParams(layoutParams)
                gravity = Gravity.CENTER
                text = it
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.pitch_notes_text_size)
                )
            }
            binding.aNotes.addView(textView)
        })
        viewModel.question.observe(viewLifecycleOwner, Observer {
            val layoutParams = LinearLayout.LayoutParams(
                resources.getDimensionPixelSize(R.dimen.pitch_notes_size),
                resources.getDimensionPixelSize(R.dimen.pitch_notes_size)
            )
            it.forEach { noteName ->
                val textView = TextView(requireContext()).apply {
                    setLayoutParams(layoutParams)
                    gravity = Gravity.CENTER
                    text = noteName
                    setTextSize(
                        TypedValue.COMPLEX_UNIT_PX,
                        resources.getDimension(R.dimen.pitch_notes_text_size)
                    )
                }
                binding.qNotes.addView(textView)
            }
        })

        viewModel.fetchQuestion()
    }

    override fun onPlay(note: Int) {
        viewModel.setPlayedNote(note)
    }
}
