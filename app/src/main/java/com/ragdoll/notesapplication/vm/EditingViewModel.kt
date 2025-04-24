package com.ragdoll.notesapplication.vm

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.ragdoll.notesapplication.data.model.Note
import kotlinx.coroutines.launch

class EditingViewModel(app: Application) : BaseViewModel(app) {
    // Update Note
    fun upsertNote(note: Note) = viewModelScope.launch { repo.upsertNote(note) }
    // Delete Note
    fun deleteNote(note: Note) = viewModelScope.launch { repo.deleteNote(note) }
}