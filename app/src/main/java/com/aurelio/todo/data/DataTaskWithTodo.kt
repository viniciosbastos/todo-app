package com.aurelio.todo.data

import androidx.room.Embedded
import androidx.room.Relation

data class DataTaskWithTodo(
    @Embedded
    val task: DataTask,

    @Relation(entity = DataToDo::class, parentColumn = "id", entityColumn = "task_id")
    val todos: List<DataToDo>
)