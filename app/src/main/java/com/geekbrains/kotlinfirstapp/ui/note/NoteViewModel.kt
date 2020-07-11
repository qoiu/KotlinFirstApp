package com.geekbrains.kotlinfirstapp.ui.note

import androidx.lifecycle.ViewModel
import com.geekbrains.kotlinfirstapp.data.Repository
import com.geekbrains.kotlinfirstapp.data.entity.Note

class NoteViewModel : ViewModel() {
    private var pendingNote: Note? = null
    fun save(note: Note){
        pendingNote=note
    }

    override fun onCleared() {
        pendingNote?.let {
            Repository.saveNote(it)
        }
    }
}