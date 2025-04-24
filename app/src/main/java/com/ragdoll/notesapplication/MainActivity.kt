package com.ragdoll.notesapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.ragdoll.notesapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        binding.addNoteFab.setOnClickListener {
            startActivity(Intent(this, AddingNoteActivity::class.java))
        }

        viewModel.getNotes().observe(this) { notes ->
            val details = notes.map { it.details }
            val adapter =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, details)
            binding.notesList.adapter = adapter

            binding.notesList.setOnItemClickListener { _, _, position, _ ->
                val intent = Intent(this, EditingNoteActivity::class.java)
                intent.putExtra("noteDetails", notes[position])
                startActivity(intent)

            }
        }

        binding.topAppToolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.favorites_item -> {
                    Toast.makeText(this, "Favorites Clicked!", Toast.LENGTH_SHORT)
                        .show()
                    true
                }

                R.id.delete_all_item -> {
                    viewModel.deleteAllNotes()
                    Toast.makeText(this, "All Notes Deleted!", Toast.LENGTH_SHORT)
                        .show()
                    true
                }

                else -> false
            }
        }

        /*binding.notesList.setOnItemClickListener { _, _, position, _ ->
            val note = viewModel.getNotes().value?.get(position)
            if (note != null) {
                val intent = Intent(this, EditingNoteActivity::class.java)
                intent.putExtra("noteDetails", note.details)
                startActivity(intent)
            }
        }*/
    }
}