package com.ragdoll.notesapplication.vm

import android.app.Application
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(app: Application) : BaseViewModel(app) {
    // Fetch ALL Notes
    fun getNotes() = repo.getNotes()
    // Delete ALL Note
    fun deleteAllNotes() = viewModelScope.launch { repo.deleteAllNotes() }
}