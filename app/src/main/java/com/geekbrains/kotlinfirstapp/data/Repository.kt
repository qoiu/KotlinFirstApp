package com.geekbrains.kotlinfirstapp.data

import com.geekbrains.kotlinfirstapp.data.entity.Note

class Repository(val provider: DataProvider) {

    fun getNotes()  = provider.subscribeToAllNotes()
    fun saveNote(note: Note)  = provider.saveNote(note)
    fun getNoteById(id: String)  = provider.getNoteById(id)
    fun getCurrentUser() = provider.getCurrentUser()
    fun deleteNote(noteId: String) = provider.deleteNote(noteId)
}