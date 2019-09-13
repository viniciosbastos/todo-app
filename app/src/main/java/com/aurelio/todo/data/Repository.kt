package com.aurelio.todo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

class Repository {

    private val tasksList = mutableListOf<Task>()
    private val _tasks = MutableLiveData<List<Task>>()
    var tasks: LiveData<List<Task>>

    init {
        _tasks.value = mutableListOf()
        tasks = _tasks
    }

    fun addTask(task: Task) {
        tasksList.add(task)
        _tasks.value = tasksList.toList()
    }

    companion object {
        private lateinit var INSTANCE: Repository
        fun getIntance(): Repository {
            if (INSTANCE != null)
                INSTANCE = Repository()
            return INSTANCE
        }
    }
}