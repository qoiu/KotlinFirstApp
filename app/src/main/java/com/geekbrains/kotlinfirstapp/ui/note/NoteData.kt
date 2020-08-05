package com.geekbrains.kotlinfirstapp.ui.note

import com.geekbrains.kotlinfirstapp.data.entity.Note
import com.geekbrains.kotlinfirstapp.ui.base.BaseViewState

class NoteData(val isDeleted: Boolean=false, val note: Note? = null)