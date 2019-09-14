package com.aurelio.todo.data

data class ToDo (
    var id: Int? = null,
    var description: String = "",
    var finished: Boolean = false
)