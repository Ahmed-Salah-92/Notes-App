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

class EditingNoteActivity : AppCompatActivity() {
    private lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityEditingNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        val receivedNote =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                intent.getParcelableExtra("noteDetails", Note::class.java)!!
            else
                intent.getParcelableExtra("noteDetails")!!

        binding.noteTitleEt.setText(receivedNote.details)

        binding.updateNoteBtn.setOnClickListener {
            val updatedNote = binding.noteTitleEt.text.toString()
            viewModel.upsertNote(Note(receivedNote.id, updatedNote))
            finish()
        }

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
}