package com.aurelio.todo.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aurelio.todo.data.Repository

class TasksViewModel : ViewModel() {
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

    private var repository: Repository = Repository.getInstance()

    val tasks = repository.tasks

    fun addTask() {
        _navigateToAdd.value = true
    }

    fun onTaskItemClicked(taskId: Int ){
        _navigateToEdit.value = taskId
    }

}
