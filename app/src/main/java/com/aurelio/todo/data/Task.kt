package com.aurelio.todo.data

data class Task (
    var id: Int,
    var description: String,
    var todos: List<ToDo>
)