package com.geekbrains.kotlinfirstapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){
    private val model=Model()
    private val viewStringLiveData = MutableLiveData<String>()
    private val viewCounterData = MutableLiveData<Int>()

    init {
        model.getStringLiveData().observeForever{str->
            viewStringLiveData.value=str
        }
    }

    fun viewState() : LiveData<String> = viewStringLiveData

}