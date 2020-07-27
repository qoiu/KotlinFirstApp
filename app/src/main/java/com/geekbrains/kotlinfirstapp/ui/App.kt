package com.geekbrains.kotlinfirstapp.ui

import android.app.Application
import com.geekbrains.kotlinfirstapp.di.appModule
import com.geekbrains.kotlinfirstapp.di.mainModule
import com.geekbrains.kotlinfirstapp.di.noteModule
import com.geekbrains.kotlinfirstapp.di.splashModule
import org.koin.core.Koin
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin { modules(appModule, splashModule, mainModule, noteModule) }
    }
}