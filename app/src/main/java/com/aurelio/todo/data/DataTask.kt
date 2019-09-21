package com.aurelio.todo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class DataTask (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var description: String = "",

    @ColumnInfo(name = "created_at")
    var createdAt: Long = System.currentTimeMillis()
)