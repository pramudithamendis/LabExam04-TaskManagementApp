package com.example.labexam04_taskmanagementapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity

data class Task(var item:String?){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
