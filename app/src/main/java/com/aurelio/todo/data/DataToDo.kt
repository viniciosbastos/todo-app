package com.aurelio.todo.data

import androidx.room.*

@Entity(tableName = "to_do", indices = [Index(value = ["task_id"])], foreignKeys = [ForeignKey(entity = DataTask::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("task_id"),
    onDelete = ForeignKey.CASCADE)]
)
data class DataToDo (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "task_id")
    var taskId: Int = 0,
    var description: String = "",
    var finished: Boolean = false
)