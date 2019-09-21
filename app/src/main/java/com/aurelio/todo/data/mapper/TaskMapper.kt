package com.aurelio.todo.data.mapper

import com.aurelio.todo.data.DataTask
import com.aurelio.todo.data.DataTaskWithTodo
import com.aurelio.todo.data.Task

class TaskMapper : Mapper<Task, DataTaskWithTodo> {
    private val todoMapper = ToDoMapper()

    override fun fromEntity(entity: DataTaskWithTodo): Task {
        return Task(
            id = entity.task.id,
            description = entity.task.description,
            createdAt =  entity.task.createdAt,
            todos = entity.todos.map { todoMapper.fromEntity(it) }
        )
    }

    override fun fromDomain(domain: Task): DataTaskWithTodo {
        return DataTaskWithTodo(
            task = DataTask(
                    description = domain.description,
                    createdAt =  domain.createdAt
                ),
            todos = domain.todos.map { todoMapper.fromDomain(it) }
        )
    }
}