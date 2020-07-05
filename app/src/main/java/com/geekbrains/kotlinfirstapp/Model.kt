package com.geekbrains.kotlinfirstapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Model {

    private val stringLiveData = MutableLiveData<String>()

    init {
        stringLiveData.value = "Hello world!"
    }

    fun getStringLiveData(): LiveData<String> = stringLiveData

}