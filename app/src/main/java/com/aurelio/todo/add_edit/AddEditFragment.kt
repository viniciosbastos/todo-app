package com.aurelio.todo.add_edit

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.aurelio.todo.R
import com.aurelio.todo.databinding.FragmentAddEditBinding

class AddEditFragment : Fragment() {

    private val viewModel: AddEditViewModel by lazy { ViewModelProviders.of(this).get(AddEditViewModel::class.java) }
    private lateinit var binding: FragmentAddEditBinding
    private lateinit var menu: Menu

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val taskId: Int = AddEditFragmentArgs.fromBundle(arguments!!).taskId
        if (taskId > 0) Toast.makeText(context, "taskId: $taskId", Toast.LENGTH_LONG).show()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_edit, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.todoText.setOnFocusChangeListener { _, hasFocus ->  binding.doneCheck.visibility = if (hasFocus) View.VISIBLE else View.GONE}
        binding.todoText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addTodo()
                true
            }
            false
        }
        val adapter = TodoListAdapter()
        binding.todoListRecyclerView.adapter = adapter
        viewModel.getTodoList().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.navigateToTasks.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(AddEditFragmentDirections.actionAddEditFragmentToTasksFragment())
                viewModel.doneNavigating()
            }
        })
        viewModel.isTaskValid.observe(viewLifecycleOwner, Observer { valid ->
            menu.findItem(R.id.done_action).isVisible = valid
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun addTodo() {
        viewModel.addTodo()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        this.menu = menu
        inflater.inflate(R.menu.action_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.createTask()
        return true
    }
}
