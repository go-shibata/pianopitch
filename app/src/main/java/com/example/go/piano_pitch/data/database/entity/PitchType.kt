package com.example.go.piano_pitch.data.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "pitch_type", indices = [Index(value = ["id"], unique = true)])
data class PitchType(
    @PrimaryKey var id: Long,
    var clearedTimes: Int
)