package com.ragdoll.notesapplication

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.ragdoll.notesapplication.databinding.ActivityEditingNoteBinding
import com.ragdoll.notesapplication.data.model.Note
import com.ragdoll.notesapplication.vm.EditingViewModel

class EditingNoteActivity : AppCompatActivity() {
    private var _binding: ActivityEditingNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EditingViewModel
    private lateinit var receivedNote: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityEditingNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this)[EditingViewModel::class.java]

        receivedNoteFromHomeActivity()
        initUpdateButton(receivedNote)
        initDeleteButton()
    }

    private fun receivedNoteFromHomeActivity() {
        receivedNote =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                intent.getParcelableExtra("noteDetails", Note::class.java)!!
            else
                intent.getParcelableExtra("noteDetails")!!
        binding.noteTitleEt.setText(receivedNote.details)
    }

    private fun initUpdateButton(receivedNote: Note) {
        binding.updateNoteBtn.setOnClickListener {
            val updatedNote = binding.noteTitleEt.text.toString()
            viewModel.upsertNote(Note(receivedNote.id, updatedNote))
            finish()
        }
    }

    private fun initDeleteButton() {
        binding.deleteNoteBtn.setOnClickListener { showAlertDialog(receivedNote) }
    }

    fun showAlertDialog(note: Note) {
        val builder = AlertDialog.Builder(this)
        builder
            .setTitle("Delete Note")
            .setMessage("Are you sure you want to delete this note?")
            .setPositiveButton("YES") { dialog, which ->
                viewModel.deleteNote(note)
                Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show()
                finish()
            }
            .setNegativeButton("NO") { dialog, which -> dialog.dismiss() }
            .create()
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Clean up the binding reference to avoid memory leaks
        binding.updateNoteBtn.setOnClickListener(null)
        binding.deleteNoteBtn.setOnClickListener(null)
        _binding = null
    }
}