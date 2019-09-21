package com.aurelio.todo.tasks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aurelio.todo.data.Repository
import com.aurelio.todo.data.TodoAppDatabase

class TasksViewModel(application: Application) : AndroidViewModel(application) {
    private val _navigateToAdd = MutableLiveData<Boolean>()
    val navigateToAdd: LiveData<Boolean>
        get() = _navigateToAdd

    private val _navigateToEdit = MutableLiveData<Int>()
    val navigateToEdit: LiveData<Int>
        get() = _navigateToEdit

    fun doneNavigatingAdd() {
        _navigateToAdd.value = false
    }

    fun doneNavigatingEdit() {
        _navigateToEdit.value = null
    }

    private var repository: Repository

    init {
        val database = TodoAppDatabase.getDatabase(application)
        repository =  Repository(database.taskDao(), database.todoDao())
    }

    val tasks = repository.getTasks()

    fun addTask() {
        _navigateToAdd.value = true
    }

    fun onTaskItemClicked(taskId: Int ){
        _navigateToEdit.value = taskId
    }

}
