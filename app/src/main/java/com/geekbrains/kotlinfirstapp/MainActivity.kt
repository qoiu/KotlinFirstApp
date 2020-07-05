package com.geekbrains.kotlinfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private val liveData = MutableLiveData<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.viewState().observe(this, Observer {str->
            Toast.makeText(this,str,Toast.LENGTH_LONG).show()
        })
        findViewById<Button>(R.id.addBtn).setOnClickListener { view->
            Toast.makeText(this,"btnClicked",Toast.LENGTH_LONG).show()
        }
    }

}