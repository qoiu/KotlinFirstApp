package com.geekbrains.kotlinfirstapp.ui.main

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.firebase.ui.auth.AuthUI
import com.geekbrains.kotlinfirstapp.R
import com.geekbrains.kotlinfirstapp.data.entity.Note
import com.geekbrains.kotlinfirstapp.ui.base.BaseActivity
import com.geekbrains.kotlinfirstapp.ui.note.NoteActivity
import com.geekbrains.kotlinfirstapp.ui.splash.SplashActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<List<Note>?>() {
    private lateinit var adapter: NotesRVAdapter

    companion object {
        fun start(context: Context) = Intent(context, MainActivity::class.java).apply {
            context.startActivity(this)
        }
    }

    override val viewModel: MainViewModel by viewModel()
    override val layoutRes = R.layout.activity_main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        mainRecycler.layoutManager = GridLayoutManager(this, 2)
        adapter = NotesRVAdapter {
            NoteActivity.start(this, it.id)
        }
        mainRecycler.adapter = adapter

        fab.setOnClickListener {
            NoteActivity.start(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean = MenuInflater(this).inflate(R.menu.main,menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.logout -> showLogoutDialog().let { true }
        else -> false
    }


    private fun showLogoutDialog() {
        val note2:Note=Note()
        supportFragmentManager.findFragmentByTag(LogoutDialog.TAG)
                ?: LogoutDialog.createInstance(positiveListener = DialogInterface.OnClickListener { dialog, which ->      Log.e("Btn","pr")
                    AuthUI.getInstance()
                            .signOut(this)
                            .addOnCompleteListener {
                                startActivity(Intent(this, SplashActivity::class.java))
                                finish()
                            }
                }).show(supportFragmentManager, LogoutDialog.TAG)
    }

    override fun renderData(data: List<Note>?) {
        data?.let {
        adapter.notes = it
    }
    }

}