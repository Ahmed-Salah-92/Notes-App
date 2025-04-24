package com.ragdoll.notesapplication.data

import androidx.lifecycle.LiveData
import com.ragdoll.notesapplication.data.model.Note
import com.ragdoll.notesapplication.data.model.NoteDAO

class NoteRepositoryImpl(private val noteDAO: NoteDAO) : NoteRepository {

    override fun upsertNote(note: Note): Unit = noteDAO.upsertNote(note)

    override fun getNotes(): LiveData<List<Note>> = noteDAO.getNotes()

    override fun deleteNote(note: Note) = noteDAO.deleteNote(note)

    override fun deleteAllNotes() = noteDAO.deleteAllNotes()
}