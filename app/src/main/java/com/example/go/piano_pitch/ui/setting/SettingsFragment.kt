package com.example.go.piano_pitch.ui.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.go.piano_pitch.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}
