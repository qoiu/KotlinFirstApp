package com.geekbrains.kotlinfirstapp.ui.note

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import com.geekbrains.kotlinfirstapp.data.Repository
import com.geekbrains.kotlinfirstapp.data.entity.Note
import com.geekbrains.kotlinfirstapp.data.model.NoteResult
import com.geekbrains.kotlinfirstapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class NoteViewModel(val repository: Repository) : BaseViewModel<NoteData>() {

    private val pendingNote: Note?
        get() = getViewState().poll()?.note

    fun save(note: Note) {
        setData(NoteData(note =note))
    }

    fun loadNote(noteId: String) = launch{
        try {
            repository.getNoteById(noteId).let {
                setData(NoteData(note=it))
            }
        }catch (e: Throwable){
            setError(e)
        }
    }

    fun deleteNote() = launch{
        try {
            pendingNote?.let {
                repository.deleteNote(it.id)
                setData(NoteData(isDeleted = true))
            }
        }catch (e: Throwable){
            setError(e)
        }
    }

    @VisibleForTesting
    public override fun onCleared() {
        launch {
            pendingNote?.let {
                repository.saveNote(it)
            }
        }
    }
}