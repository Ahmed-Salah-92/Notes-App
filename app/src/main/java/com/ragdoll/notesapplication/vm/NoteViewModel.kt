package com.ragdoll.notesapplication.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ragdoll.notesapplication.data.NoteRepositoryImpl
import com.ragdoll.notesapplication.data.model.Note
import com.ragdoll.notesapplication.data.model.RoomDBHelper

class NoteViewModel(app: Application) : AndroidViewModel(app) {

    private val repository: NoteRepositoryImpl

    init {
        val db = RoomDBHelper.Companion.getInstance(app)
        repository = NoteRepositoryImpl(db.dao)
    }


    fun upsertNote(note: Note) = repository.upsertNote(note)
    fun deleteNote(note: Note) = repository.deleteNote(note)
    fun getNotes() = repository.getNotes()
    fun deleteAllNotes() = repository.deleteAllNotes()
}