package com.ragdoll.notesapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.ragdoll.notesapplication.data.model.Note
import com.ragdoll.notesapplication.databinding.ActivityMainBinding
import com.ragdoll.notesapplication.vm.NoteViewModel

class HomeActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NoteViewModel
    private lateinit var notes: List<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        // Observe the notes LiveData from the ViewModel
        viewModel.getNotes().observe(this) { notes ->
            // Update the UI with the list of notes
            this.notes = notes
            val details = notes.map { it.details }
            // Setup the adapter for the ListView
            val adapter =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, details)
            binding.notesList.adapter = adapter

            initOnItemClick()
        }

        initTopAppBar()
        initFloatActionButton()

    }

    private fun initTopAppBar() {
        binding.topAppToolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.favorites_item -> {
                    Toast.makeText(this, "Favorites Clicked!", Toast.LENGTH_SHORT)
                        .show()
                    true
                }

                R.id.delete_all_item -> {
                    showAlertDialog()
                    true
                }

                else -> false
            }
        }
    }

    fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder
            .setTitle("Delete All Note".uppercase())
            .setMessage("Are you sure you want to delete all notes?")
            .setPositiveButton("YES") { dialog, which ->
                viewModel.deleteAllNotes()
                Toast.makeText(this, "All Notes Deleted!", Toast.LENGTH_SHORT)
                    .show()
            }
            .setNegativeButton("NO") { dialog, which -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun initFloatActionButton() {
        binding.addNoteFab.setOnClickListener { navigateToAddActivity() }
    }

    private fun navigateToAddActivity() {
        startActivity(Intent(this, AddingNoteActivity::class.java))
    }

    private fun initOnItemClick() {
        binding.notesList.setOnItemClickListener { _, _, position, _ ->
            navigateToEditActivity(position)
        }
    }

    private fun navigateToEditActivity(position: Int) {
        val intent = Intent(this, EditingNoteActivity::class.java)
        intent.putExtra("noteDetails", this.notes[position])
        startActivity(intent)
    }


    override fun onDestroy() {
        super.onDestroy()
        // Clean up the binding reference to avoid memory leaks
        binding.addNoteFab.setOnClickListener(null)
        binding.notesList.onItemClickListener = null
        binding.topAppToolbar.setOnMenuItemClickListener(null)
        _binding = null
    }
}