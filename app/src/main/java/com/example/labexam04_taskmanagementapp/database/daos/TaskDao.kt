package com.example.labexam04_taskmanagementapp.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.labexam04_taskmanagementapp.database.entities.Task

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)
    @Delete
    suspend fun deleteTask(task: Task)
    @Query("SELECT * FROM Task")
    fun getAllTasks():List<Task>

    @Query("UPDATE Task SET item=:item WHERE item=:currentitem ")
    suspend fun edit(item:String, currentitem:String)

}