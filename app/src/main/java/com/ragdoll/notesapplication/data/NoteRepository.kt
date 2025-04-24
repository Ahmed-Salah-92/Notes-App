package com.ragdoll.notesapplication.data

import androidx.lifecycle.LiveData
import com.ragdoll.notesapplication.data.model.Note

interface NoteRepository {
   suspend fun upsertNote(note: Note)

    fun getNotes(): LiveData<List<Note>>

   suspend fun deleteNote(note: Note)

   suspend fun deleteAllNotes()

}