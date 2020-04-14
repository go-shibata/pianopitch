package com.example.go.piano_pitch.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.go.piano_pitch.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMenuBinding.inflate(inflater, container, false).apply {
            fragment = this@MenuFragment
        }
        return binding.root
    }

    fun onClickPitchButton() {
        findNavController().navigate(
            MenuFragmentDirections.actionMenuFragmentToPitchFragment()
        )
    }

    fun onClickPianoButton() {
        findNavController().navigate(
            MenuFragmentDirections.actionMenuFragmentToPianoFragment()
        )
    }
}
