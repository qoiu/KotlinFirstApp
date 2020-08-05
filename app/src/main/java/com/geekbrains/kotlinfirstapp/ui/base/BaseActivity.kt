package com.geekbrains.kotlinfirstapp.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.firebase.ui.auth.AuthUI
import com.geekbrains.kotlinfirstapp.R
import com.geekbrains.kotlinfirstapp.data.error.NoAuthException
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<S> : AppCompatActivity() , CoroutineScope {


    companion object {
        private const val REQUEST_CODE = 4242
    }

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Main + Job()
    }

     private lateinit var dataJob: Job
     private lateinit var errorJob: Job
    abstract val viewModel: BaseViewModel<S>
    abstract val layoutRes: Int?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutRes?.let {
            setContentView(it)
        }
    }

    override fun onStart() {
        super.onStart()
        dataJob = launch {
            viewModel.getViewState().consumeEach {
                renderData(it)
            }
        }
        errorJob = launch {
            viewModel.getErrorChannel().consumeEach {
                renderError(it)
            }
        }
    }

    protected fun renderError(error: Throwable) {
        when (error) {
            is NoAuthException -> startLogin()
            else -> error?.message?.let {
                showError(it)
            }
        }
        error?.message?.let {
            showError(it)
        }
    }

    fun showError(eror: String) {
        Toast.makeText(this, eror, Toast.LENGTH_SHORT).show()
    }

    abstract fun renderData(data: S)

    fun startLogin() {
        val providers = listOf(
                AuthUI.IdpConfig.GoogleBuilder().build()
        )
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setLogo(R.drawable.android_robot)
                        .setTheme(R.style.LoginStyle)
                        .setAvailableProviders(providers)
                        .build()
                , REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE && resultCode!= Activity.RESULT_OK){
            finish()
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStop() {
        super.onStop()
        dataJob.cancel()
        errorJob.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancel()
    }
}