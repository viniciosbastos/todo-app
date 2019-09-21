package com.aurelio

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.aurelio.todo.data.Task
import com.aurelio.todo.data.TodoAppDatabase
import com.aurelio.todo.data.mapper.TaskMapper
import com.aurelio.todo.tasks.data.TaskDao
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class TaskDatabaseTest {
    private lateinit var tasksDao: TaskDao
    private lateinit var db: TodoAppDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(
            context, TodoAppDatabase::class.java).build()
        tasksDao = db.taskDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun saveTaskAndReadAllTasks() {
        val task = Task(description = "TESTE")
        val task2 = Task(description = "TESTE2")
        var mapper = TaskMapper()
        tasksDao.insert(mapper.fromDomain(task).task)
        tasksDao.insert(mapper.fromDomain(task2).task)

        val foundTask = tasksDao.getTasks_()
        assertEquals(2, foundTask.size)
    }
}