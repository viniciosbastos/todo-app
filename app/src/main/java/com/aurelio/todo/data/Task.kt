package com.aurelio.todo.data

data class Task (
    var id: Int? = null,
    var description: String = "",
    var todos: MutableList<ToDo> = mutableListOf()
)