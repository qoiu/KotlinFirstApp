package com.geekbrains.kotlinfirstapp.ui.note

import com.geekbrains.kotlinfirstapp.data.Repository
import com.geekbrains.kotlinfirstapp.data.entity.Note
import com.geekbrains.kotlinfirstapp.data.model.NoteResult
import com.geekbrains.kotlinfirstapp.ui.base.BaseViewModel

class NoteViewModel : BaseViewModel<Note?,NoteViewState>() {
    private var pendingNote: Note? = null

    fun save(note: Note){
        pendingNote=note
    }

    fun loadNote(noteId: String){
        Repository.getNoteById(noteId).observeForever {result ->
            result ?: return@observeForever
            when(result){
                is NoteResult.Success<*> -> viewStateLiveData.value= NoteViewState(note = result.data as? Note)
                is NoteResult.Error -> viewStateLiveData.value= NoteViewState(error = result.error)
            }
        }
    }

    override fun onCleared() {
        pendingNote?.let {
            Repository.saveNote(it)
        }
    }
}