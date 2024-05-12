package com.example.labexam04_taskmanagementapp

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val textt:TextView
    val delete: Button
    val edit: Button
    init {
        textt = view.findViewById(R.id.textView)
        delete = view.findViewById(R.id.button2)
        edit = view.findViewById(R.id.edit)
    }
}