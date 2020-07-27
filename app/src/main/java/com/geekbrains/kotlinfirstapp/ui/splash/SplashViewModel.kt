package com.geekbrains.kotlinfirstapp.ui.splash

import androidx.lifecycle.Observer
import com.geekbrains.kotlinfirstapp.data.Repository
import com.geekbrains.kotlinfirstapp.data.common.observeOnce
import com.geekbrains.kotlinfirstapp.data.error.NoAuthException
import com.geekbrains.kotlinfirstapp.ui.base.BaseViewModel

class SplashViewModel(val repository: Repository): BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser(){
        repository.getCurrentUser().observeOnce(Observer {
            viewStateLiveData.value = it?.let {
                SplashViewState(authorized = true)
            } ?: let {
                SplashViewState(error = NoAuthException())
            }
        })
    }
}