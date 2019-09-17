package com.aurelio.todo.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun getDateFromMillis(millis: Long): String {
    return SimpleDateFormat("dd/MM/yyyy HH:mm").format(Date(millis))
}