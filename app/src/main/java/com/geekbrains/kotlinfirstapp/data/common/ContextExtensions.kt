package com.geekbrains.kotlinfirstapp.data.common

import android.content.Context


fun Context.dip(value: Int): Int = (value * (resources?.displayMetrics?.density ?: 0f)).toInt()
fun Context.dip(value: Float): Int = (value * (resources?.displayMetrics?.density ?: 0f)).toInt()