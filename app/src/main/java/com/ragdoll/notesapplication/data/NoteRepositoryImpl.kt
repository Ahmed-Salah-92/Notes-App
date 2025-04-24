package com.ragdoll.notesapplication.data

import androidx.lifecycle.LiveData
import com.ragdoll.notesapplication.data.model.Note
import com.ragdoll.notesapplication.data.model.NoteDAO

class NoteRepositoryImpl(private val noteDAO: NoteDAO) : NoteRepository {

    override suspend fun upsertNote(note: Note) = noteDAO.upsertNote(note)

    override fun getNotes(): LiveData<List<Note>> = noteDAO.getNotes()

    override suspend fun deleteNote(note: Note) = noteDAO.deleteNote(note)

    override suspend fun deleteAllNotes() = noteDAO.deleteAllNotes()
}