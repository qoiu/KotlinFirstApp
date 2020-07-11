package com.geekbrains.kotlinfirstapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geekbrains.kotlinfirstapp.data.entity.Note
import java.util.*

object Repository {
    private val noteLiveData=MutableLiveData<List<Note>>()

    private var notes: MutableList<Note> = mutableListOf(
            Note(UUID.randomUUID().toString(),"Note 1", "this is first note", Note.Color.WHITE),
            Note(UUID.randomUUID().toString(),"Note 2", "this is second note", Note.Color.BLUE),
            Note(UUID.randomUUID().toString(),"Note 3", "this is third note", Note.Color.YELLOW),
            Note(UUID.randomUUID().toString(),"Note 4", "this is fourth note", Note.Color.RED),
            Note(UUID.randomUUID().toString(),"Note 5", "this is fifth note", Note.Color.GREEN),
            Note(UUID.randomUUID().toString(),"Note 6", "this is sixth note", Note.Color.ORANGE)
    )

    init {
        noteLiveData.value= notes
    }

    fun saveNote(note: Note){
        addOrReplace(note)
        noteLiveData.value= notes
    }

    private fun addOrReplace(note: Note){
        for(i in 0 until notes.size){
            if(notes[i] == note){
                notes[i]=note
                return
            }
        }
        notes.add(note)
    }
    fun getNote(): LiveData<List<Note>> {
        return noteLiveData
    }
}