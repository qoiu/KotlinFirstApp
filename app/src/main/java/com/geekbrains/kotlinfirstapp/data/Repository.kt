package com.geekbrains.kotlinfirstapp.data

import com.geekbrains.kotlinfirstapp.App
import com.geekbrains.kotlinfirstapp.data.entity.Note

object Repository {
    private var notes: MutableList<Note> = mutableListOf(
            Note("Note 1", "this is first note", 0xfff062f0.toInt()),
            Note("Note 2", "this is second note", 0xfff062f0.toInt()),
            Note("Note 3", "this is third note", 0xfff0f092.toInt()),
            Note("Note 4", "this is fourth note", 0xfff0f0f0.toInt()),
            Note("Note 5", "this is fifth note", 0xff926292.toInt()),
            Note("Note 6", "this is sixth note", 0xff626292.toInt())
    )
    init {
        for(i in App.titles.indices){
            notes.add(Note(i))
        }
    }

    fun getNote(): List<Note> {
        return notes
    }
}