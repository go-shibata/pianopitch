package com.example.go.piano_pitch.ui.piano

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.go.piano_pitch.databinding.FragmentPianoBinding
import com.example.go.piano_pitch.di.ViewModelFactory
import com.example.go.piano_pitch.ui.MainActivityViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PianoFragment : Fragment() {

    @Inject
    lateinit var mainActivityFactory: ViewModelFactory<MainActivityViewModel>
    private val mainActivityViewModel: MainActivityViewModel
            by activityViewModels { mainActivityFactory }

    private lateinit var binding: FragmentPianoBinding

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPianoBinding.inflate(inflater, container, false).apply {
            piano.setPianoPlayer(mainActivityViewModel.pianoPlayer)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivityViewModel.onLoadComplete.observe(viewLifecycleOwner, Observer {
            binding.piano.setTouchable(true)
        })
    }
}
