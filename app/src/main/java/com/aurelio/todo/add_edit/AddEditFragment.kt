package com.aurelio.todo.add_edit

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil

import com.aurelio.todo.R
import com.aurelio.todo.databinding.FragmentAddEditBinding

class AddEditFragment : Fragment() {

    private val viewModel: AddEditViewModel by lazy { ViewModelProviders.of(this).get(AddEditViewModel::class.java) }
    private lateinit var binding: FragmentAddEditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_edit, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.todoText.setOnFocusChangeListener { _, hasFocus ->  if (hasFocus) binding.doneCheck.visibility = View.VISIBLE}
        binding.todoText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addTodo()
                true
            }
            false
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun addTodo() {
        viewModel.addTodo()
        resetInputs()
    }

    private fun resetInputs() {
        binding.todoText.text = null
        binding.doneCheck.isChecked = false
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        createTask()
        return true
    }

    private fun createTask() {
        Toast.makeText(context, "Done", Toast.LENGTH_LONG).show()
    }


}
