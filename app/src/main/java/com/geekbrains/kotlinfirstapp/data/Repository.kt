package com.geekbrains.kotlinfirstapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geekbrains.kotlinfirstapp.data.entity.Note
import com.geekbrains.kotlinfirstapp.data.entity.User
import java.util.*

object Repository {

    private val remoteProvider: DataProvider = FirestoreDataProvider()
    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
    fun getCurrentUser(): LiveData<User> = remoteProvider.getCurrentUser();
}