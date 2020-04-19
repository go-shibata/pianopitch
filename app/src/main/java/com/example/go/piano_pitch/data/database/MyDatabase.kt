package com.example.go.piano_pitch.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.go.piano_pitch.data.database.dao.PitchTypeDao
import com.example.go.piano_pitch.data.database.entity.PitchType

@Database(
    entities = [
        PitchType::class
    ], version = 1, exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun pitchTypeDao(): PitchTypeDao

    companion object {
        @Volatile
        private var instance: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase =
            instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    MyDatabase::class.java,
                    "PianoPitch.db"
                ).build()
                    .also { instance = it }
            }
    }
}