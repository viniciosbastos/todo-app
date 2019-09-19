package com.aurelio.todo.data

import androidx.room.Embedded
import androidx.room.Relation

data class TaskWithToDos(
    @Embedded
    val task: Task,

    @Relation(parentColumn = "id", entityColumn = "taskId", entity = ToDo::class)
    val todos: List<ToDo>
);