package com.aurelio.todo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Repository private constructor(){

    private val tasksList = mutableListOf<Task>()
    private val _tasks = MutableLiveData<List<Task>>()
    var tasks: LiveData<List<Task>>

    init {
        _tasks.value = mutableListOf()
        tasks = _tasks
    }

    fun addTask(task: Task) {
        task.id =  tasksList.size + 1
        tasksList.add(task)
        _tasks.value = tasksList.toList()
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

    companion object {
        private var INSTANCE: Repository? = null
        fun getInstance(): Repository {
            if (INSTANCE == null)
                INSTANCE = Repository()
            return INSTANCE!!
        }
    }
}