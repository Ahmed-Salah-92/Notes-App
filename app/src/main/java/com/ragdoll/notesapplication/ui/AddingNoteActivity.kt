package com.ragdoll.notesapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.ragdoll.notesapplication.R
import com.ragdoll.notesapplication.data.model.Note
import com.ragdoll.notesapplication.databinding.ActivityAddingNoteBinding
import com.ragdoll.notesapplication.vm.AddingViewModel

class AddingNoteActivity : AppCompatActivity() {
    private var _binding: ActivityAddingNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AddingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityAddingNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this)[AddingViewModel::class.java]

        initializeSaveButton()
    }

    private fun initializeSaveButton() {
        binding.saveNoteBtn.setOnClickListener {
            val details = binding.noteTitleEt.text.toString()
            val note = Note(details = details)
            viewModel.upsertNote(note)
            binding.noteTitleEt.text.clear()
            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Clean up the binding reference to avoid memory leaks
        binding.saveNoteBtn.setOnClickListener(null)
        _binding = null
    }
}