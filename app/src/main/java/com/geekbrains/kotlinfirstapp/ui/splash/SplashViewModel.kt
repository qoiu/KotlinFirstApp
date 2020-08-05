package com.geekbrains.kotlinfirstapp.ui.splash

import androidx.lifecycle.Observer
import com.geekbrains.kotlinfirstapp.data.Repository
import com.geekbrains.kotlinfirstapp.data.common.observeOnce
import com.geekbrains.kotlinfirstapp.data.error.NoAuthException
import com.geekbrains.kotlinfirstapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SplashViewModel(val repository: Repository): BaseViewModel<Boolean>() {

    fun requestUser() = launch{
        repository.getCurrentUser()?.let {
            setData(true)
        }?:let {
            setError(NoAuthException())
        }
    }
}