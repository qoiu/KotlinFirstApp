package com.geekbrains.kotlinfirstapp.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.kotlinfirstapp.R
import com.geekbrains.kotlinfirstapp.data.entity.Note
import com.geekbrains.kotlinfirstapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_note.*
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : BaseActivity<Note?, NoteViewState>() {

    companion object {
        private val EXTRA_NOTE = "extraNote"
        private const val DATE_TIME_FORMAT = "dd.MM.yy HH:mm"
        fun start(context: Context, noteId: String? = null) = Intent(context, NoteActivity::class.java).run {
            putExtra(EXTRA_NOTE, noteId)
            context.startActivity(this)
        }
    }

    private var note: Note? = null
    override  val viewModel: NoteViewModel by lazy {
        ViewModelProvider(this).get(NoteViewModel::class.java)
    }
    override val layoutRes = R.layout.activity_note

    lateinit var noteColor: Note.Color

    val textChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            saveNote()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val noteId = intent.getStringExtra(EXTRA_NOTE)
        noteId?.let{
            viewModel.loadNote(it)
        } ?: let {
            supportActionBar?.title = getString(R.string.note_new)
        }
        noteColor= note?.color ?: Note.Color.WHITE
        colorBtn.setOnClickListener {
            noteColor=Note.Color.values()[
                    if (noteColor.ordinal+1 < Note.Color.values().size) noteColor.ordinal+1
                    else Note.Color.WHITE.ordinal
            ]
            saveNote()
        }

    }

    override fun renderData(data: Note?) {
        this.note = data
        supportActionBar?.title = note?.let {
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(it.lastChanged)
        } ?: let {
            getString(R.string.note_new)
        }
        initView()
    }

    private fun Note.Color.getColor()= when (this) {
        Note.Color.ORANGE -> R.color.color_orange
        Note.Color.GREEN -> R.color.color_green
        Note.Color.RED -> R.color.color_red
        Note.Color.YELLOW -> R.color.color_yellow
        Note.Color.BLUE -> R.color.color_blue
        Note.Color.WHITE -> R.color.color_white
        Note.Color.VIOLET -> R.color.color_violet
    }


    private fun initView() {
        titleEt.removeTextChangedListener(textChangeListener)
        bodyEt.removeTextChangedListener(textChangeListener)

        note?.let {
            titleEt.setText(it.title)
            bodyEt.setText(it.text)
            resetColor()
        }

        titleEt.addTextChangedListener(textChangeListener)
        bodyEt.addTextChangedListener(textChangeListener)
    }

    fun resetColor(){
        note?.let {
        toolbar.setBackgroundColor(ResourcesCompat.getColor(resources, it.color.getColor(), null))
        colorBtn.setBackgroundColor(ResourcesCompat.getColor(resources, it.color.getColor(), null))
        }
    }

    fun saveNote() {
        if (titleEt.text.isNullOrBlank()) return
        note = note?.copy(
                text = bodyEt.text.toString(),
                title = titleEt.text.toString(),
                color = noteColor,
                lastChanged = Date()
        ) ?: Note(UUID.randomUUID().toString(),title = titleEt.text.toString(),text = bodyEt.text.toString())
        note?.let {
            viewModel.save(it)
        }
        resetColor()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        android.R.id.home->{
            onBackPressed()
            true
        }
        else-> {Toast.makeText(this,item.toString(),Toast.LENGTH_LONG).show()
        super.onOptionsItemSelected(item)}
    }
}