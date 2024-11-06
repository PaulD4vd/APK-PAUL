package com.example.paul1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class FourthActivity : AppCompatActivity() {

    private lateinit var tvNoteDisplay: TextView
    private lateinit var etNoteInput: EditText
    private val notesList = mutableListOf<String>()  // Untuk menyimpan catatan yang disimpan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fourth_activity)

        tvNoteDisplay = findViewById(R.id.tvNoteDisplay)
        etNoteInput = findViewById(R.id.etNoteInput)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnDeleteLast = findViewById<Button>(R.id.btnDeleteLast)
        val btnClearAll = findViewById<Button>(R.id.btnClearAll)

        // Tombol Save
        btnSave.setOnClickListener {
            saveNote()
        }

        // Tombol Delete Last
        btnDeleteLast.setOnClickListener {
            deleteLastNote()
        }

        // Tombol Clear All
        btnClearAll.setOnClickListener {
            clearAllNotes()
        }
    }

    private fun saveNote() {
        val noteText = etNoteInput.text.toString().trim()
        if (noteText.isNotEmpty()) {
            // Format timestamp
            val timestamp = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())
            val formattedNote = "• $noteText ($timestamp)"

            // Tambahkan catatan ke daftar dan tampilkan
            notesList.add(formattedNote)
            displayNotes()

            // Kosongkan input setelah disimpan
            etNoteInput.text.clear()
        }
    }

    private fun deleteLastNote() {
        if (notesList.isNotEmpty()) {
            // Hapus catatan terakhir
            notesList.removeAt(notesList.size - 1)
            displayNotes()
        }
    }

    private fun clearAllNotes() {
        notesList.clear()
        displayNotes()
    }

    private fun displayNotes() {
        // Gabungkan semua catatan dalam satu teks untuk ditampilkan
        tvNoteDisplay.text = notesList.joinToString("\n\n")
    }
}
