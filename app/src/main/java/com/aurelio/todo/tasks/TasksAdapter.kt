package com.aurelio.todo.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aurelio.todo.R
import com.aurelio.todo.data.Task
import com.aurelio.todo.databinding.ListItemTaskBinding
import com.aurelio.todo.util.getDateFromMillis

class TasksAdapter: ListAdapter<Task, TasksAdapter.ViewHolder>(TaskDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(val binding: ListItemTaskBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.descriptionText.text = task.description
            binding.countText.text = "Count: ${task.todos.size}"
            binding.createdAt.text = getDateFromMillis(task.createdAt)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ListItemTaskBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_task, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class TaskDiffCallback: DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}