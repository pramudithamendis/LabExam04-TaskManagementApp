package com.example.labexam04_taskmanagementapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.labexam04_taskmanagementapp.database.entities.Task

class MainActivityData:ViewModel() {
    private val _data = MutableLiveData<List<Task>>()
    val data:LiveData<List<Task>> = _data
    fun setData(data:List<Task>){
        _data.value = data
    }
}