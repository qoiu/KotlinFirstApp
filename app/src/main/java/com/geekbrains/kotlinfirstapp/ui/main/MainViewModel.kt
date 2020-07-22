package com.geekbrains.kotlinfirstapp.ui.main

import androidx.lifecycle.Observer
import com.geekbrains.kotlinfirstapp.data.Repository
import com.geekbrains.kotlinfirstapp.data.entity.Note
import com.geekbrains.kotlinfirstapp.data.model.NoteResult
import com.geekbrains.kotlinfirstapp.ui.base.BaseViewModel

class MainViewModel : BaseViewModel<List<Note>?,MainViewState>(){


    private val noteObserver = Observer{result: NoteResult ->
        when(result){
            is NoteResult.Success<*> -> viewStateLiveData.value = MainViewState(notes = result.data as? List<Note>)
            is NoteResult.Error -> viewStateLiveData.value = MainViewState(error = result.error)
        }
    }

    private val repository = Repository.getNotes()

    init {
        Repository.getNotes().observeForever{
            viewStateLiveData.value= MainViewState()
            repository.observeForever(noteObserver)
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.removeObserver(noteObserver)
    }
}