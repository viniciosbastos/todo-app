package com.aurelio.todo.add_edit

import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import com.aurelio.todo.data.Task
import com.aurelio.todo.data.ToDo

class AddEditViewModel : ViewModel() {

    private val task: Task
    private val todo: ToDo

    init {
        task = Task()
        todo = ToDo()
    }

//    fun getTaskDescription(): String = task.description

    var taskDescription: String? = null
        @Bindable
        set

    var todoDescription: String? = null
        @Bindable
        set

    var todoChecked: Boolean? = null
        @Bindable
        set

//    fun getTodoDescription(): String = todo.description
//
//    @Bindable
//    fun setTodoDescription(value: String) {
//        if (todo.description != value)
//            todo.description = value
//    }
//
//    fun getTodoChecked(): Boolean = todo.finished
//
//    @Bindable
//    fun setTodoChecked(value: Boolean) {
//        if (todo.finished != value)
//            todo.finished = value
//    }

}
