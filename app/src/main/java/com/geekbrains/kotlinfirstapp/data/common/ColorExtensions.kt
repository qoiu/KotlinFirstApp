package com.geekbrains.kotlinfirstapp.data.common

import android.content.Context
import androidx.core.content.ContextCompat
import com.geekbrains.kotlinfirstapp.R
import com.geekbrains.kotlinfirstapp.data.entity.Note

fun Note.Color.getColor(context: Context): Int= ContextCompat.getColor(context,when(this){
Note.Color.ORANGE -> R.color.color_orange
    Note.Color.GREEN -> R.color.color_green
    Note.Color.RED -> R.color.color_red
    Note.Color.YELLOW -> R.color.color_yellow
    Note.Color.BLUE -> R.color.color_blue
    Note.Color.WHITE -> R.color.color_white
    Note.Color.VIOLET -> R.color.color_violet
}
)