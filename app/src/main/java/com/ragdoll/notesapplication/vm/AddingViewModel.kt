package com.ragdoll.notesapplication.vm

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.ragdoll.notesapplication.data.model.Note
import kotlinx.coroutines.launch

class AddingViewModel(app: Application) : BaseViewModel(app) {
    // Insert Note
     fun upsertNote(note: Note) = viewModelScope.launch { repo.upsertNote(note) }
}