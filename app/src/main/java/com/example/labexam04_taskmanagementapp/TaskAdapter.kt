package com.example.labexam04_taskmanagementapp.adapters

import android.content.Context
import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.labexam04_taskmanagementapp.MainActivityData
import com.example.labexam04_taskmanagementapp.R
import com.example.labexam04_taskmanagementapp.TaskViewHolder
import com.example.labexam04_taskmanagementapp.database.entities.Task
import com.example.labexam04_taskmanagementapp.database.repositories.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskAdapter(items:List<Task>, itemm:String, repository: TaskRepository, viewModel: MainActivityData): RecyclerView.Adapter<TaskViewHolder>() {
    var context: Context? = null
    val items = items
    val repository = repository
    val viewModel = viewModel
    val item = itemm
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task,parent,false)
        return TaskViewHolder(view)

    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.textt.text = items.get(position).item
        holder.delete.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                repository.delete(items.get(position))
                val data = repository.getAll()
                withContext(Dispatchers.Main){
                    viewModel.setData(data)
                }
            }
        }
        holder.edit.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
//                repository.delete(items.get(position))
                val item  = item
                val currentitem = items.get(position).item.toString()
                repository.edit(item,currentitem)
                val data = repository.getAll()
                withContext(Dispatchers.Main){
                    viewModel.setData(data)
                }
            }
        }
    }

}