package com.geekbrains.kotlinfirstapp.ui.main

import com.geekbrains.kotlinfirstapp.data.entity.Note
import com.geekbrains.kotlinfirstapp.ui.base.BaseViewState

class MainViewState (val notes:List<Note>? = null, error: Throwable? = null) : BaseViewState<List<Note>?>(notes,error)