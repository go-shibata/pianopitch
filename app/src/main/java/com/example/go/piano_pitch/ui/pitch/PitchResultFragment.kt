package com.example.go.piano_pitch.ui.pitch

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.go.piano_pitch.data.Result
import com.example.go.piano_pitch.databinding.FragmentPitchResultBinding
import com.example.go.piano_pitch.di.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PitchResultFragment : Fragment(), PitchResultEpoxyController.OnClickPlayButtonListener {

    @Inject
    lateinit var factory: ViewModelFactory<PitchResultViewModel>
    private val viewModel: PitchResultViewModel by viewModels { factory }

    @Inject
    lateinit var epoxyController: PitchResultEpoxyController

    val onLoadComplete = {
        epoxyController.setCanPlay(true)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        viewModel // to init
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkNotNull(arguments).let {
            val results = PitchResultFragmentArgs.fromBundle(it).results
            epoxyController.setData(results.toList())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPitchResultBinding.inflate(inflater, container, false).apply {
            results.setController(epoxyController)
        }
        return binding.root
    }

    override fun onClickPlayButton(result: Result) {
        viewModel.play(result)
    }
}
