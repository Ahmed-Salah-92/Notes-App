package com.ragdoll.notesapplication

import androidx.lifecycle.LiveData
import com.ragdoll.notesapplication.data.model.Note
import com.ragdoll.notesapplication.data.model.NoteDAO

class Repository(private val noteDAO: NoteDAO) : NoteDAO {
    /**
    Repository class that implements the NoteDAO interface.
    This class acts as a bridge between the data source (Room database) and the ViewModel.
    It provides methods to perform CRUD operations on the Note entity.
    @param noteDAO The DAO (Data Access Object) for the Note entity.
    */

    override suspend fun upsertNote(note: Note): Unit = noteDAO.upsertNote(note)

    override fun getNotes(): LiveData<List<Note>> = noteDAO.getNotes()

    override suspend fun deleteNote(note: Note) = noteDAO.deleteNote(note)

    override suspend fun deleteAllNotes() = noteDAO.deleteAllNotes()
}