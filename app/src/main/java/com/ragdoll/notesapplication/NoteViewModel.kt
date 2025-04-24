package com.ragdoll.notesapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class NoteViewModel(app: Application) : AndroidViewModel(app) {

    private val db = RoomDBHelper.getInstance(app)

    fun upsertNote(note: Note) = db.dao.upsertNote(note)
    fun deleteNote(note: Note) = db.dao.deleteNote(note)
    fun getNotes() = db.dao.getNotes()
    fun deleteAllNotes() = db.dao.deleteAllNotes()
}