package com.aurelio.todo.tasks.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aurelio.todo.data.DataTask
import com.aurelio.todo.data.DataTaskWithTodo
import com.aurelio.todo.data.Task

@Dao
interface TaskDao {

    @Transaction @Query("select * from task order by created_at desc")
    fun getTasks(): LiveData<List<DataTaskWithTodo>>

    @Query("select * from task where description = :description")
    fun getTaskByDescription(description: String): DataTask

    @Insert
    suspend fun insert(task: DataTask)

    @Update
    fun update(task: DataTask)
}