package com.geekbrains.kotlinfirstapp.di

import com.geekbrains.kotlinfirstapp.data.DataProvider
import com.geekbrains.kotlinfirstapp.data.FirestoreDataProvider
import com.geekbrains.kotlinfirstapp.data.Repository
import com.geekbrains.kotlinfirstapp.ui.main.MainViewModel
import com.geekbrains.kotlinfirstapp.ui.note.NoteViewModel
import com.geekbrains.kotlinfirstapp.ui.splash.SplashViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { FirebaseFirestore.getInstance() }
    single { FirebaseAuth.getInstance() }
    single<DataProvider> { FirestoreDataProvider(get(),get()) }
    single { Repository(get()) }

}
val splashModule = module {
    viewModel { SplashViewModel(get()) }
}
val mainModule = module {
    viewModel { MainViewModel(get()) }
}
val noteModule = module {
    viewModel { NoteViewModel(get()) }
}