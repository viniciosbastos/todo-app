package com.aurelio.todo.data.mapper

import com.aurelio.todo.data.DataToDo
import com.aurelio.todo.data.ToDo

class ToDoMapper : Mapper<ToDo, DataToDo> {
    override fun fromEntity(entity: DataToDo): ToDo {
        return ToDo(id = entity.id!!, description = entity.description, finished = entity.finished)
    }

    override fun fromDomain(domain: ToDo): DataToDo {
        return DataToDo(id = domain.id, description = domain.description, finished = domain.finished)
    }

}