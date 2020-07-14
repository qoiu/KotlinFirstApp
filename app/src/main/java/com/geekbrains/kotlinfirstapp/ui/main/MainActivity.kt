package com.geekbrains.kotlinfirstapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.geekbrains.kotlinfirstapp.R
import com.geekbrains.kotlinfirstapp.data.entity.Note
import com.geekbrains.kotlinfirstapp.ui.note.NoteActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainRecycler.layoutManager=GridLayoutManager(this,2)
        adapter = NotesRVAdapter{
            NoteActivity.start(this,it)
        }
        mainRecycler.adapter=adapter
        viewModel.viewState().observe(this, Observer {state->
            state?.let { adapter.notes=it.notes }
        })

        fab.setOnClickListener {
            NoteActivity.start(this,null)
        }
    }

}