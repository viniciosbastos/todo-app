package com.aurelio.todo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.aurelio.todo.data.mapper.TaskMapper
import com.aurelio.todo.tasks.data.TaskDao
import com.aurelio.todo.tasks.data.ToDoDao
import kotlinx.coroutines.Dispatchers

class TaskRepository constructor(private val taskDao: TaskDao, private val todoDao: ToDoDao){

    private val tasksList = mutableListOf<Task>()
    private val tasks: LiveData<List<Task>>
        get() = taskDao.getTasks().map { tasks -> tasks.map { mapper.fromEntity(it) } }
    val mapper = TaskMapper()


    suspend fun addTask(task: Task) {
        task.id =  tasksList.size + 1
        taskDao.insert(mapper.fromDomain(task).task)
    }

    fun getTask(taskId: Int): Task {
        val task = tasksList.find { it.id == taskId }
        return task!!
    }

    fun update(task: Task) {
        var index = tasksList.indexOf(tasksList.find { it.id == task.id })
        tasksList.removeAt(index)
        tasksList.add(index, task)
    }
}