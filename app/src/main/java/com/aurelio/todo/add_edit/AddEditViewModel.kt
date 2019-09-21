package com.aurelio.todo.add_edit

import android.app.Application
import androidx.lifecycle.*
import com.aurelio.todo.data.Repository
import com.aurelio.todo.data.Task
import com.aurelio.todo.data.ToDo
import com.aurelio.todo.data.TodoAppDatabase

class AddEditViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: Repository
    private var task: Task? = null

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
        val database = TodoAppDatabase.getDatabase(application)
        repository = Repository(database.taskDao(), database.todoDao())
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

    fun done() {
        if (task != null)
            updateTask()
        else
            createTask()
    }

    private fun updateTask() {
        task?.description = taskDescription.value!!
        task?.todos = todoList
        repository.update(task!!)
        _navigateToTasks.value = true
    }

    private fun createTask() {
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

    fun loadTask(taskId: Int) {
        task = repository.getTask(taskId)

        task?.let{
            taskDescription.value = it.description
            todoList.addAll(it.todos)
            _todoList.value = todoList
        }
    }
}
