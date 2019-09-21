package com.aurelio.todo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

data class Task (
    var id: Int? = null,
    var description: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    var todos: List<ToDo> = listOf()
)