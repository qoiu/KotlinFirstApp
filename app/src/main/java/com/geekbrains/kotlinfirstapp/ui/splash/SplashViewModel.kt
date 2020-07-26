package com.geekbrains.kotlinfirstapp.ui.splash

import androidx.lifecycle.Observer
import com.geekbrains.kotlinfirstapp.data.Repository
import com.geekbrains.kotlinfirstapp.data.error.NoAuthException
import com.geekbrains.kotlinfirstapp.ui.base.BaseViewModel

class SplashViewModel(): BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser(){
        Repository.getCurrentUser().observeForever {
            viewStateLiveData.value = it?.let {
                SplashViewState(authorized = true)
            } ?: let {
                SplashViewState(error = NoAuthException())
            }
        }
    }
}