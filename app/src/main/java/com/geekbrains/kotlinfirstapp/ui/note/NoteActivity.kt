package com.geekbrains.kotlinfirstapp.ui.note

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.kotlinfirstapp.R
import com.geekbrains.kotlinfirstapp.data.common.getColor
import com.geekbrains.kotlinfirstapp.data.entity.Note
import com.geekbrains.kotlinfirstapp.ui.base.BaseActivity
import com.geekbrains.kotlinfirstapp.ui.main.LogoutDialog
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_note.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : BaseActivity<NoteViewState.Data, NoteViewState>() {

    companion object {
        private const val EXTRA_NOTE = "extraNote"
        private const val DATE_TIME_FORMAT = "dd.MM.yy HH:mm"
        fun start(context: Context, noteId: String? = null) = Intent(context, NoteActivity::class.java).run {
            putExtra(EXTRA_NOTE, noteId)
            context.startActivity(this)
        }
    }

    private var note: Note? = null
    override val layoutRes: Int = R.layout.activity_note
    override val viewModel: NoteViewModel by viewModel()

    val textChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            saveNote()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean = MenuInflater(this).inflate(R.menu.menu_note, menu).let { true }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val noteId = intent.getStringExtra(EXTRA_NOTE)
        noteId?.let {
            if (savedInstanceState == null)
                viewModel.loadNote(it)
        } ?: let {
            supportActionBar?.title = getString(R.string.note_new)
        }

    }

    override fun renderData(data: NoteViewState.Data) {
        if(data.isDeleted)finish()
        this.note = data.note
        supportActionBar?.title = note?.let {
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(it.lastChanged)
        } ?: let {
            getString(R.string.note_new)
        }
        initView()
    }

    private fun initView() {
        titleEt.removeTextChangedListener(textChangeListener)
        bodyEt.removeTextChangedListener(textChangeListener)

        note?.let {
            if (it.title != titleEt.text.toString()) titleEt.setText(it.title)
            if (it.text != bodyEt.text.toString()) bodyEt.setText(it.text)
            resetColor()
        }

        titleEt.addTextChangedListener(textChangeListener)
        bodyEt.addTextChangedListener(textChangeListener)
        colorPicker.onColorClickListener = {
            note=note?.copy(color = it)
            resetColor()
            saveNote()
        }
    }

    private fun resetColor() {
        note?.let {
            toolbar.setBackgroundColor(it.color.getColor(this))
            appbar.setBackgroundColor(it.color.getColor(this))
        }

        //toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.color_black))
    }

    fun saveNote() {
        if (titleEt.text.isNullOrBlank()) return
        note = note?.copy(
                text = bodyEt.text.toString(),
                title = titleEt.text.toString(),
                lastChanged = Date()
        )
                ?: Note(UUID.randomUUID().toString(), title = titleEt.text.toString(), text = bodyEt.text.toString())
        note?.let {
            viewModel.save(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> onBackPressed().let { true }
        R.id.delete -> deleteNote().let { true }
        R.id.palette -> togglePalette().let { true }
        else -> super.onOptionsItemSelected(item)

    }

    private fun deleteNote(){
        LogoutDialog(positiveListener = DialogInterface.OnClickListener { _, _ -> viewModel.deleteNote()}, title = getString(R.string.dialog_delete))
                .show(supportFragmentManager, LogoutDialog.TAG)

    }

    private fun togglePalette(){
        if(colorPicker.isOpen){
            colorPicker.close()
        } else{
            colorPicker.open()
        }
    }

}