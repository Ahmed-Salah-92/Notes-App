package com.ragdoll.notesapplication.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ragdoll.notesapplication.data.NoteRepositoryImpl
import com.ragdoll.notesapplication.data.model.Note
import com.ragdoll.notesapplication.data.model.RoomDBHelper
import kotlinx.coroutines.launch

class EditingViewModel(app: Application) : AndroidViewModel(app) {
    private val repository: NoteRepositoryImpl

    init {
        val db = RoomDBHelper.getInstance(app)
        repository = NoteRepositoryImpl(db.dao)
    }

    // Update Note
    fun upsertNote(note: Note) = viewModelScope.launch { repository.upsertNote(note) }
    fun deleteNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }
}