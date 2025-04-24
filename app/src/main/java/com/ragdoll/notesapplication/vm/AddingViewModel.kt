package com.ragdoll.notesapplication.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ragdoll.notesapplication.data.NoteRepositoryImpl
import com.ragdoll.notesapplication.data.model.Note
import com.ragdoll.notesapplication.data.model.RoomDBHelper

class AddingViewModel(app: Application) : AndroidViewModel(app) {
    private val repository: NoteRepositoryImpl

    init {
        val db = RoomDBHelper.getInstance(app)
        repository = NoteRepositoryImpl(db.dao)
    }

    // Insert Note
    fun upsertNote(note: Note) = repository.upsertNote(note)
}