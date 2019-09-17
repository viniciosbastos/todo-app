package com.aurelio.todo.add_edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aurelio.todo.data.Repository
import com.aurelio.todo.data.Task
import com.aurelio.todo.data.ToDo

class AddEditViewModel : ViewModel() {
    private var repository: Repository

    val taskDescription = MutableLiveData<String>()
    val todoDescription = MutableLiveData<String>()
    val todoFinished = MutableLiveData<Boolean>()

    private val todoList = mutableListOf<ToDo>()
    private val _todoList = MutableLiveData<List<ToDo>>()

    private val _navigateToTasks = MutableLiveData<Boolean>()
    val navigateToTasks: LiveData<Boolean>
        get() = _navigateToTasks

    init {
        todoFinished.value = false
        repository = Repository.getInstance()
        _navigateToTasks.value = false
    }

    fun getTodoList(): LiveData<List<ToDo>> = _todoList

    fun addTodo() {
        var todoId = todoList.size + 1
        var todo = ToDo(id = todoId, description = todoDescription.value!!, finished = todoFinished.value!!)
        todoList.add(todo)
        _todoList.value = todoList
        resetInputs()
    }

    private fun resetInputs() {
        todoDescription.value = ""
        todoFinished.value = false
    }

    fun createTask() {
        val task = Task(description = taskDescription.value!!, todos = todoList)
        repository.addTask(task)
        _navigateToTasks.value = true
    }

    fun doneNavigating() {
        _navigateToTasks.value = false
    }

    val isTaskValid: LiveData<Boolean> = Transformations.map(taskDescription) { taskText ->
        !taskText.isNullOrEmpty()
    }
}
