package com.qersh.swimmig_schole_api.dp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qersh.swimmig_schole_api.model.note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): Flow<List<note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes: note)

    @Query("DELETE FROM notes_table Where coach_name=:coach_name")
    suspend fun deleteNote(coach_name: String)

    @Query("SELECT EXISTS(SELECT * FROM notes_table WHERE coach_name = :coach_name)")
    fun isRowIsExist(coach_name: String) : Boolean



}