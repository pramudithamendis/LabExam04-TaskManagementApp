package com.example.labexam04_taskmanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.labexam04_taskmanagementapp.adapters.TaskAdapter
import com.example.labexam04_taskmanagementapp.database.TaskDB
import com.example.labexam04_taskmanagementapp.database.entities.Task
import com.example.labexam04_taskmanagementapp.database.repositories.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: TaskAdapter
    private lateinit var viewModel:MainActivityData
//    private lateinit var edit:EditText
    private lateinit var editInput:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = TaskRepository(TaskDB.getInstance(this))
        val rvTaskList: RecyclerView = findViewById(R.id.rvTaskList)
        viewModel = ViewModelProvider(this)[MainActivityData::class.java]
        editInput = findViewById(R.id.editInput)

        viewModel.data.observe(this){
            val adapter = TaskAdapter(it, editInput.text.toString(), repository, viewModel)
            rvTaskList.adapter = adapter
            rvTaskList.layoutManager = LinearLayoutManager(this)
        }

        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getAll()
            runOnUiThread{
                viewModel.setData(data)
            }
            val button4: Button = findViewById(R.id.button4)
            button4.setOnClickListener{
                displayDialog(repository)
            }
        }

    }
    fun displayDialog(repository: TaskRepository){
        val builder = AlertDialog.Builder(this)
        // Set the alert dialog title and message
        builder.setTitle("Enter task name")
//        builder.setMessage("Enter the todo item below:")
        // Create an EditText input field
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        // Set the positive button action
        builder.setPositiveButton("OK") { dialog, which ->
// Get the input text and display a Toast message
            val item = input.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                repository.insert(Task(item))
                val data = repository.getAll()
                runOnUiThread {
                    viewModel.setData(data)
                }
            }
        }
        // Set the negative button action
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }
// Create and show the alert dialog
        val alertDialog = builder.create()
        alertDialog.show()
    }
    fun usingTextBoxToGrapInput(repository: TaskRepository){

    }
}