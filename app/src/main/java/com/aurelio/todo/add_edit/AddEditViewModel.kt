package com.aurelio.todo.add_edit

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aurelio.todo.data.Task
import com.aurelio.todo.data.ToDo

class AddEditViewModel : ViewModel() {

    val taskDescription = MutableLiveData<String>()
    val todoDescription = MutableLiveData<String>()
    val todoFinished = MutableLiveData<Boolean>()

    private val todoList = mutableListOf<ToDo>()
    private val _todoList = MutableLiveData<List<ToDo>>()

    init {
        todoFinished.value = false
    }

    fun getTodoList(): LiveData<List<ToDo>> = Transformations.map(_todoList) {
        _todoList.value
    }
    fun getTodoCount(): LiveData<Int> = Transformations.map(_todoList) {
        _todoList.value?.size
    }

    fun addTodo() {
        var todo = ToDo(description = todoDescription.value!!, finished = todoFinished.value!!)
        todoList.add(todo)
        _todoList.value = todoList
        resetInputs()
    }

    private fun resetInputs() {
        todoDescription.value = ""
        todoFinished.value = false
    }
}
