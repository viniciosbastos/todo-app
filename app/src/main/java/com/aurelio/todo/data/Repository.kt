package com.aurelio.todo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

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

    companion object {
        private var INSTANCE: Repository? = null
        fun getInstance(): Repository {
            if (INSTANCE == null)
                INSTANCE = Repository()
            return INSTANCE!!
        }
    }
}