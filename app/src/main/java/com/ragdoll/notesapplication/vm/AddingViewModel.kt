package com.ragdoll.notesapplication.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ragdoll.notesapplication.data.NoteRepositoryImpl
import com.ragdoll.notesapplication.data.model.Note
import com.ragdoll.notesapplication.data.model.RoomDBHelper
import kotlinx.coroutines.launch

class AddingViewModel(app: Application) : AndroidViewModel(app) {
    private val repository: NoteRepositoryImpl

    init {
        val db = RoomDBHelper.getInstance(app)
        repository = NoteRepositoryImpl(db.dao)
    }

    // Insert Note
     fun upsertNote(note: Note) = viewModelScope.launch { repository.upsertNote(note) }

}