package com.geekbrains.kotlinfirstapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.kotlinfirstapp.data.Repository

class MainViewModel : ViewModel(){
    private val viewStateLiveData = MutableLiveData<MainViewState>()

    init {
     viewStateLiveData.value= MainViewState(Repository.getNote())
    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData

}