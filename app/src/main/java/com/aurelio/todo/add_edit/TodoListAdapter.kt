package com.aurelio.todo.add_edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aurelio.todo.R
import com.aurelio.todo.data.ToDo
import com.aurelio.todo.databinding.ListItemTodoBinding

class TodoListAdapter: ListAdapter<ToDo, TodoListAdapter.ViewHolder>(TodoListDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(val binding: ListItemTodoBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: ToDo) {
            binding.todo = todo
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ListItemTodoBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_todo, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class TodoListDiff: DiffUtil.ItemCallback<ToDo>() {
    override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
        return oldItem == newItem
    }

}