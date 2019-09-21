package com.aurelio.todo.tasks.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.aurelio.todo.data.DataToDo

@Dao
interface ToDoDao {

    @Insert
    fun insert(todo: DataToDo)

    @Update
    fun update(todo: DataToDo)
}