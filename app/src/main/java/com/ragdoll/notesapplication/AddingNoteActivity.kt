package com.ragdoll.notesapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.ragdoll.notesapplication.databinding.ActivityAddingNoteBinding

class AddingNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityAddingNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        binding.saveNoteBtn.setOnClickListener {
            val details = binding.noteTitleEt.text.toString()
            val note = Note(details = details)
            viewModel.upsertNote(note)
            binding.noteTitleEt.text.clear()
            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show()
        }

    }
}