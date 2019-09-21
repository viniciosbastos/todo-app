package com.aurelio.todo.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database
import com.aurelio.todo.tasks.data.TaskDao
import com.aurelio.todo.tasks.data.ToDoDao

@Database(entities = [DataTask::class, DataToDo::class], version = 1, exportSchema = false)
abstract class TodoAppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
    abstract fun todoDao(): ToDoDao

    companion object {
        @Volatile
        private var INSTANCE: TodoAppDatabase? = null

        fun getDatabase(context: Context): TodoAppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoAppDatabase::class.java,
                    "todo_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}