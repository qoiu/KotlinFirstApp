package com.geekbrains.kotlinfirstapp.data.entity

import android.graphics.Color
import com.geekbrains.kotlinfirstapp.App

class Note (var title: String, var text: String, val color: Int) {
    constructor(id: Int, title: String="", text: String="",
                color: Int=Color.rgb((75 until 180).random(),(75 until 180).random(), (75 until 180).random())) : this(title,text,color) {
        this.title= App.titles[id]
        this.text= App.textes[id]
    }
}