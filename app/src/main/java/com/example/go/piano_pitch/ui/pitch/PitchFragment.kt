package com.example.go.piano_pitch.ui.pitch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.go.piano_pitch.databinding.FragmentPitchBinding

class PitchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPitchBinding.inflate(inflater, container, false)
        return binding.root
    }
}
