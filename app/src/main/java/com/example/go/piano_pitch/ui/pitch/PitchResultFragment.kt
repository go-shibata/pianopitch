package com.example.go.piano_pitch.ui.pitch

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.go.piano_pitch.data.Result
import com.example.go.piano_pitch.databinding.FragmentPitchResultBinding
import com.example.go.piano_pitch.di.ViewModelFactory
import com.example.go.piano_pitch.ui.MainActivityViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PitchResultFragment : Fragment(), PitchResultEpoxyController.OnClickPlayButtonListener {

    @Inject
    lateinit var factory: ViewModelFactory<PitchResultViewModel>
    private val viewModel: PitchResultViewModel by viewModels { factory }

    @Inject
    lateinit var mainActivityFactory: ViewModelFactory<MainActivityViewModel>
    private val mainActivityViewModel: MainActivityViewModel
            by activityViewModels { mainActivityFactory }

    @Inject
    lateinit var epoxyController: PitchResultEpoxyController

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        viewModel // to init
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkNotNull(arguments).let {
            val results = PitchResultFragmentArgs.fromBundle(it).results
            val pitchTypeId = PitchResultFragmentArgs.fromBundle(it).pitchTypeId
            epoxyController.setData(results.toList())
            viewModel.saveResult(pitchTypeId, results.toList())
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivityViewModel.onLoadComplete.observe(viewLifecycleOwner, Observer {
            epoxyController.setCanPlay(true)
        })
    }

    override fun onClickPlayButton(result: Result) {
        viewModel.play(result)
    }
}
