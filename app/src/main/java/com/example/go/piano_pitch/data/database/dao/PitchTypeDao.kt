package com.example.go.piano_pitch.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.go.piano_pitch.data.database.entity.PitchType

@Dao
interface PitchTypeDao {
    @Query("select * from pitch_type")
    fun getAllPitchTypes(): LiveData<List<PitchType>>

    @Query("select * from pitch_type where id = :id")
    fun getPitchType(id: Long): LiveData<PitchType>

    @Query("select * from pitch_type where id = :id")
    fun getPitchTypeImmediate(id: Long): PitchType?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(pitchType: PitchType)

    @Update
    fun update(pitchType: PitchType)

    @Delete
    fun delete(pitchType: PitchType)

    @Query("delete from pitch_type")
    fun deleteAll()
}