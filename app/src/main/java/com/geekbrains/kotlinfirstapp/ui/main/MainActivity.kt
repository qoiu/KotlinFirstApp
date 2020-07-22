package com.geekbrains.kotlinfirstapp.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.geekbrains.kotlinfirstapp.R
import com.geekbrains.kotlinfirstapp.data.entity.Note
import com.geekbrains.kotlinfirstapp.ui.base.BaseActivity
import com.geekbrains.kotlinfirstapp.ui.note.NoteActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<List<Note>?, MainViewState>() {
    private lateinit var adapter: NotesRVAdapter

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    override val layoutRes = R.layout.activity_main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        mainRecycler.layoutManager=GridLayoutManager(this,2)
        adapter = NotesRVAdapter{
            NoteActivity.start(this,it.id)
        }
        mainRecycler.adapter=adapter

        fab.setOnClickListener {
            NoteActivity.start(this)
        }
    }

    override fun renderData(data: List<Note>?) {
        data?.let {
            adapter.notes = it
        }
    }
}