package com.caaron.todolistfragment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.caaron.todolistfragment.MyApplication
import com.caaron.todolistfragment.R
import com.caaron.todolistfragment.databinding.FragmentAddItemBinding
import com.caaron.todolistfragment.model.Task
import com.caaron.todolistfragment.viewModels.AddTaskViewModel
import com.google.android.material.snackbar.Snackbar


class AddItemFragment : Fragment() {
    private lateinit var binding: FragmentAddItemBinding
    private val viewModel: AddTaskViewModel by viewModels {
        AddTaskViewModel.Provider((requireContext().applicationContext as MyApplication).taskRepo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val details = binding.etDetails.text.toString()
            val selectedId: Int = binding.radioG.checkedRadioButtonId
            val radioName = resources.getResourceEntryName(selectedId)
            val color = when (radioName.toString()) {
                "radio_green" -> "#00E676"
                "radio_blue" -> "#2979FF"
                "radio_red" -> "#FF1744"
                "radio_orange" -> "#FF9100"
                "radio_yellow" -> "#FFEA00"
                else -> "#FFFFFF"
            }
            if (title.isEmpty() || details.isEmpty()) {
                val snackBar =
                    Snackbar.make(binding.root, "Please enter all the values", Snackbar.LENGTH_LONG)
                snackBar.show()
            } else {
                viewModel.addTask(Task(null, title, details, color))
                val bundle = Bundle()
                bundle.putBoolean("refresh", true)
                setFragmentResult("from_add_item", bundle)
                NavHostFragment.findNavController(this).popBackStack()
            }
        }
    }

}