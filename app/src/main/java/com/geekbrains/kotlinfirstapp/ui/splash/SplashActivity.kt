package com.geekbrains.kotlinfirstapp.ui.splash

import androidx.lifecycle.ViewModelProvider
import com.geekbrains.kotlinfirstapp.ui.base.BaseActivity
import com.geekbrains.kotlinfirstapp.ui.main.MainActivity
import com.geekbrains.kotlinfirstapp.R

class SplashActivity : BaseActivity<Boolean?,SplashViewState>() {
    override val viewModel: SplashViewModel by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }
    override val layoutRes: Int = R.layout.activity_splash

    override fun renderData(data: Boolean?) {
        data?.takeIf { it }?. let {
            startMainActivity()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.requestUser()
    }

    fun startMainActivity(){
        MainActivity.start(this)
        finish()
    }

}