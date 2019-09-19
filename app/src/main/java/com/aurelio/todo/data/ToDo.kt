package com.aurelio.todo.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "to_do", foreignKeys = [ForeignKey(entity = Task::class,
                                                        parentColumns = arrayOf("id"),
                                                        childColumns = arrayOf("taskId"),
                                                        onDelete = ForeignKey.CASCADE)]
)
data class ToDo (
    @PrimaryKey
    var id: Int? = null,
    var taskId: Int,
    var description: String = "",
    var finished: Boolean = false
)