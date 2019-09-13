package com.aurelio.todo.tasks

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.aurelio.todo.R
import com.aurelio.todo.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {

    private val viewModel: TasksViewModel by lazy {ViewModelProviders.of(this).get(TasksViewModel::class.java)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTasksBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tasks, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val adapter = TasksAdapter()
        binding.tasksRecyclerView.adapter = adapter
        viewModel.tasks.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })
        viewModel.navigateToAddEdit.observe(viewLifecycleOwner, Observer {
            if (it) {
                this.findNavController()
                    .navigate(TasksFragmentDirections.actionTasksFragmentToAddEditFragment())
                viewModel.doneNavigating()
            }
        })

        return binding.root
    }

}
