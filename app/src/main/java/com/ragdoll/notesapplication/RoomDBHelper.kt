package com.ragdoll.notesapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class RoomDBHelper : RoomDatabase() {

    abstract val dao: NoteDAO

    companion object {
        @Volatile
        private var INSTANCE: RoomDBHelper? = null

        fun getInstance(context: Context): RoomDBHelper {
            return INSTANCE ?: synchronized(this) {
                // Create a new instance of the database if it doesn't exist
                val newInstance = Room
                    .databaseBuilder(context, RoomDBHelper::class.java, "MyDB")
                    .allowMainThreadQueries()
                    .build()
                // Assign the new instance to INSTANCE and return it
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}