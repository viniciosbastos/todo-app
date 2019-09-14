package com.aurelio.todo.add_edit

import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import com.aurelio.todo.data.Task
import com.aurelio.todo.data.ToDo

class AddEditViewModel : ViewModel() {

    private val task: Task = Task()
    private var todo: ToDo = ToDo()

    var taskDescription: String? = null
        @Bindable
        set (value: String?) {
            field = value
            task.description = value ?: ""
        }

    var todoDescription: String? = null
        @Bindable
        set (value: String?) {
            field = value
            todo.description = value ?: ""
        }

    var todoChecked: Boolean? = null
        @Bindable
        set (value: Boolean?) {
            field = value
            todo.finished = value ?: false
        }

    fun addTodo() {
        task.todos?.add(todo)
        todo = ToDo()
    }

}
