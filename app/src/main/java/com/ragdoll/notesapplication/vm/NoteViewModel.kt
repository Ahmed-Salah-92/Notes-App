package com.ragdoll.notesapplication.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ragdoll.notesapplication.data.NoteRepositoryImpl
import com.ragdoll.notesapplication.data.model.RoomDBHelper
import kotlinx.coroutines.launch

class NoteViewModel(app: Application) : AndroidViewModel(app) {

    private val repository: NoteRepositoryImpl

    init {
        val db = RoomDBHelper.Companion.getInstance(app)
        repository = NoteRepositoryImpl(db.dao)
    }

    fun getNotes() = repository.getNotes()
    fun deleteAllNotes() = viewModelScope.launch { repository.deleteAllNotes() }
}