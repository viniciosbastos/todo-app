package com.aurelio.todo.add_edit

import android.opengl.Visibility
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAddEditBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_edit, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.todoText.setOnFocusChangeListener { view, hasFocus ->  if (hasFocus) binding.doneCheck.visibility = View.VISIBLE}
        binding.todoText.setOnEditorActionListener { view, actionId, keyEvent ->
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
        Toast.makeText(context, "ToDo added", Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.done_action) {
            createTask()
            true
        }else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun createTask() {
        Toast.makeText(context, "Done", Toast.LENGTH_LONG).show()
    }


}
