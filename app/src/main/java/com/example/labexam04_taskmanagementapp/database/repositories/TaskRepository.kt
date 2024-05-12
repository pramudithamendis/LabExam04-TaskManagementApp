package com.example.labexam04_taskmanagementapp.database.repositories

import com.example.labexam04_taskmanagementapp.database.TaskDB
import com.example.labexam04_taskmanagementapp.database.entities.Task

class TaskRepository(private val db:TaskDB) {
    suspend fun insert(task: Task)= db.getTaskDao().insertTask(task)
    suspend fun delete(task:Task) = db.getTaskDao().deleteTask(task)
    fun getAll():List<Task> = db.getTaskDao().getAllTasks()

    suspend fun edit(item:String, currentitem:String) = db.getTaskDao().edit(item, currentitem)
}
