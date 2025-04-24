package com.ragdoll.notesapplication.data

import androidx.lifecycle.LiveData
import com.ragdoll.notesapplication.data.model.Note

interface NoteRepository {
    fun upsertNote(note: Note)

    fun getNotes(): LiveData<List<Note>>

    fun deleteNote(note: Note)

    fun deleteAllNotes()

}