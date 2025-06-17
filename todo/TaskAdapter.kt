package com.example.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val tasks: MutableList<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.tasktext.text = task.title

        // Clear the listener first to prevent conflicts during recycling
        holder.cbbox.setOnCheckedChangeListener(null)
        holder.cbbox.isChecked = task.isDone
        holder.tasktext.paint.isStrikeThruText = task.isDone

        // Set checkbox listener
        holder.cbbox.setOnCheckedChangeListener { _, isChecked ->
            task.isDone = isChecked
            holder.tasktext.paint.isStrikeThruText = isChecked
        }

        // Delete button listener
        holder.deletebtn.setOnClickListener {
            tasks.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, tasks.size)
        }
    }

    override fun getItemCount(): Int = tasks.size

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cbbox: CheckBox = itemView.findViewById(R.id.checkBox)
        val tasktext: TextView = itemView.findViewById(R.id.tesktext) // Fixed typo
        val deletebtn: ImageView = itemView.findViewById(R.id.deleteBtn)
    }
}