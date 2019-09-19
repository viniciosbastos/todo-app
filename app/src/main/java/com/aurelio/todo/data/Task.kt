package com.aurelio.todo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task (
    @PrimaryKey
    var id: Int? = null,
    var description: String = "",
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)