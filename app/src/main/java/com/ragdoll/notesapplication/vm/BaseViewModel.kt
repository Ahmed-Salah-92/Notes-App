package com.ragdoll.notesapplication.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ragdoll.notesapplication.Repository
import com.ragdoll.notesapplication.data.model.RoomDBHelper

open class BaseViewModel(app: Application): AndroidViewModel(app) {
    protected val repo: Repository
    init {
        val db = RoomDBHelper.getInstance(app)
        repo = Repository(db.dao)
    }
}