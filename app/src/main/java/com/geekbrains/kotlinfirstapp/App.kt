package com.geekbrains.kotlinfirstapp

import android.app.Application

class App : Application() {
    companion object {
        lateinit var titles : Array<String>
        lateinit var textes: Array<String>
    }

    override fun onCreate() {
        super.onCreate()
        titles = this.applicationContext.resources.getStringArray(R.array.note_title)
        textes = this.applicationContext.resources.getStringArray(R.array.note_text)
    }
}