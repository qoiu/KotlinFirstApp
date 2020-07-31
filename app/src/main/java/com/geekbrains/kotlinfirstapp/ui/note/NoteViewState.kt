package com.geekbrains.kotlinfirstapp.ui.note

import com.geekbrains.kotlinfirstapp.data.entity.Note
import com.geekbrains.kotlinfirstapp.ui.base.BaseViewState

class NoteViewState(data: Data = Data(), error: Throwable? = null) : BaseViewState<NoteViewState.Data>(data,error) {
    data class Data(val isDeleted: Boolean=false, val note: Note? = null)
}