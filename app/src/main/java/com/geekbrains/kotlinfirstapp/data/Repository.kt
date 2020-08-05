package com.geekbrains.kotlinfirstapp.data

import com.geekbrains.kotlinfirstapp.data.entity.Note

class Repository(val provider: DataProvider) {

    fun getNotes()  = provider.subscribeToAllNotes()
    suspend fun saveNote(note: Note)  = provider.saveNote(note)
    suspend fun getNoteById(id: String)  = provider.getNoteById(id)
    suspend fun getCurrentUser() = provider.getCurrentUser()
    suspend fun deleteNote(noteId: String) = provider.deleteNote(noteId)
}