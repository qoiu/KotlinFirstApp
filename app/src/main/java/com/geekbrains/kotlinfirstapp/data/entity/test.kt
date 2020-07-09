package com.geekbrains.kotlinfirstapp.data.entity

import android.content.res.Resources
import com.geekbrains.kotlinfirstapp.R

class test {
    private fun func() {
        for (i in 0 until Resources.getSystem().getStringArray(R.array.note_title).size) {
            println(i)
        }
    }
}