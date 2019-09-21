package com.aurelio.todo.data

data class ToDo (
    var id: Int = 0,
    var taskId: Int = 0,
    var description: String = "",
    var finished: Boolean = false
)