package com.geekbrains.kotlinfirstapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){
    private val model=Model()
    private val viewStringLiveData = MutableLiveData<String>()
    private val viewCounterLiveData = MutableLiveData<String>()

    init {
        model.getStringLiveData().observeForever{str->
            viewStringLiveData.value=str
        }
        model.getCounterLiveData().observeForever{str->
            viewCounterLiveData.value= "Счётчик: $str"
        }
    }

    fun viewState() : LiveData<String> = viewStringLiveData
    fun counterState() : LiveData<String> = viewCounterLiveData
    fun increaseCounter(){
        model.increaseCounter()
    }
}