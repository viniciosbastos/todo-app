package com.aurelio.todo.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aurelio.todo.data.Repository
import com.aurelio.todo.data.Task
import com.aurelio.todo.data.ToDo

class TasksViewModel : ViewModel() {
    private val _navigateToAddEdit = MutableLiveData<Boolean>()
    val navigateToAddEdit: LiveData<Boolean>
        get() = _navigateToAddEdit

    fun doneNavigating() {
        _navigateToAddEdit.value = false
    }

    private var repository: Repository = Repository()

    val tasks = repository.tasks

    fun addTask() {
        _navigateToAddEdit.value = true
    }



}
