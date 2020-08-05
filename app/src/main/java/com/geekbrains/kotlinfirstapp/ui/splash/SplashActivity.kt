package com.geekbrains.kotlinfirstapp.ui.splash

import com.geekbrains.kotlinfirstapp.ui.base.BaseActivity
import com.geekbrains.kotlinfirstapp.ui.main.MainActivity
import com.geekbrains.kotlinfirstapp.R
import com.geekbrains.kotlinfirstapp.ui.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<Boolean>() {
    override val viewModel: SplashViewModel by viewModel()
    override val layoutRes: Int = R.layout.activity_splash

    override fun renderData(data: Boolean) {
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