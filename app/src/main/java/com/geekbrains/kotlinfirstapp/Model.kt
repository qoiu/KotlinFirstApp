package com.geekbrains.kotlinfirstapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Model {

    private val stringLiveData = MutableLiveData<String>()
    private val counterLiveData = MutableLiveData<Int>()

    init {
        stringLiveData.value = "Hello world!"
        counterLiveData.value = 0
    }

    fun getStringLiveData(): LiveData<String> = stringLiveData
    fun getCounterLiveData(): LiveData<Int> = counterLiveData
    fun increaseCounter(){
        counterLiveData.value = counterLiveData.value?.plus(1)
    }

}