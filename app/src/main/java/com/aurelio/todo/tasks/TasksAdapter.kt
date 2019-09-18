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

class TasksAdapter(private val clickListener: TasksItemListener): ListAdapter<Task, TasksAdapter.ViewHolder>(TaskDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder private constructor(val binding: ListItemTaskBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task, clickListener: TasksItemListener) {
            binding.listItemTask.setOnClickListener { clickListener.onClick(task) }
            binding.descriptionText.text = task.description
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

class TasksItemListener(private val clickListener: (task: Int) -> Unit) {
    fun onClick(task: Task) = clickListener(task.id!!)
}