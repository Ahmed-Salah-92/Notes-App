package com.ragdoll.notesapplication.data.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface NoteDAO {

    // Insert a new note and update if it already exists
    @Upsert
    suspend fun upsertNote(note: Note)

    @Query("SELECT * FROM note")
    fun getNotes(): LiveData<List<Note>>

    @Delete
   suspend fun deleteNote(note: Note)

    @Query("DELETE FROM note")
   suspend fun deleteAllNotes()
}