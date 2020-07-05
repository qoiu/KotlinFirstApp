package com.geekbrains.kotlinfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var counterTB: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.viewState().observe(this, Observer {str->
            Toast.makeText(this,str,Toast.LENGTH_LONG).show()
        })
        viewModel.counterState().observe(this, Observer {str->
            counterTB.text = str
        })
        findViewById<Button>(R.id.addBtn).setOnClickListener { _ ->
                viewModel.increaseCounter()
        }
    }

    private fun initView(){
        counterTB = findViewById<TextView>(R.id.addTV)
    }

}