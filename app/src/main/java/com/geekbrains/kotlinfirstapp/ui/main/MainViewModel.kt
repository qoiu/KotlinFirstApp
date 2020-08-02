package com.geekbrains.kotlinfirstapp.ui.main

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import com.geekbrains.kotlinfirstapp.data.Repository
import com.geekbrains.kotlinfirstapp.data.entity.Note
import com.geekbrains.kotlinfirstapp.data.model.NoteResult
import com.geekbrains.kotlinfirstapp.ui.base.BaseViewModel

class MainViewModel(val repository: Repository) : BaseViewModel<List<Note>?,MainViewState>(){


    private val noteObserver = Observer{result: NoteResult ->
        when(result){
            is NoteResult.Success<*> -> viewStateLiveData.value = MainViewState(notes = result.data as? List<Note>)
            is NoteResult.Error -> viewStateLiveData.value = MainViewState(error = result.error)
        }
    }

    private val repositoryNotes = repository.getNotes()

    init {
        repositoryNotes.observeForever{
            viewStateLiveData.value= MainViewState()
            repositoryNotes.observeForever(noteObserver)
        }
    }

    @VisibleForTesting
    public override fun onCleared() {
        super.onCleared()
        repositoryNotes.removeObserver(noteObserver)
    }
}