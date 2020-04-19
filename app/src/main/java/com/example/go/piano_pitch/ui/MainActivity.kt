package com.example.go.piano_pitch.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.go.piano_pitch.R
import com.example.go.piano_pitch.data.database.MyDatabase
import com.example.go.piano_pitch.data.database.entity.PitchType
import com.example.go.piano_pitch.di.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory<MainActivityViewModel>
    private val viewModel: MainActivityViewModel by viewModels { factory }

    @Inject
    lateinit var myDatabase: MyDatabase

    private lateinit var navController: NavController

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        viewModel // to init
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        initDatabasePitchType()

        navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController)
    }

    @ExperimentalStdlibApi
    fun initDatabasePitchType() {
        CoroutineScope(Dispatchers.Default).launch {
            com.example.go.piano_pitch.logic.PitchType.values().forEach {
                myDatabase.pitchTypeDao().insert(PitchType(it.id, 0))
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()
}
