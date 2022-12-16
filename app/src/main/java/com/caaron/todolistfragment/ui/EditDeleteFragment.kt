package com.caaron.todolistfragment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.caaron.todolistfragment.MyApplication
import com.caaron.todolistfragment.databinding.FragmentDetailsBinding
import com.caaron.todolistfragment.databinding.FragmentEditDeleteBinding
import com.caaron.todolistfragment.model.Task
import com.caaron.todolistfragment.viewModels.DetailsViewModel
import com.caaron.todolistfragment.viewModels.EditDeleteViewModel

class EditDeleteFragment : Fragment() {
    private lateinit var binding: FragmentEditDeleteBinding
    private lateinit var color: String
    val viewModel: EditDeleteViewModel by viewModels{
        EditDeleteViewModel.Provider(
            (requireContext().applicationContext as MyApplication).taskRepo
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditDeleteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navArgs: EditDeleteFragmentArgs by navArgs()
        viewModel.getTaskById(navArgs.id)
        viewModel.task.observe(viewLifecycleOwner){
            binding.run{
                etTitle.setText(it.title)
                etDetails.setText(it.details)
            }
        }
        binding.radioGreen.setOnClickListener {
            color = "#00E676"
        }
        binding.radioBlue.setOnClickListener {
            color = "#2979FF"
        }
        binding.radioRed.setOnClickListener {
            color = "#FF1744"
        }
        binding.radioOrange.setOnClickListener {
            color = "#FF9100"
        }
        binding.radioYellow.setOnClickListener {
            color = "#FFEA00"
        }
        binding.btnUpdate.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val details = binding.etDetails.text.toString()
            val task = Task(navArgs.id,title, details,color)
            viewModel.updateTask(navArgs.id,task)
            val bundle = Bundle()
            bundle.putBoolean("refresh",true)
            setFragmentResult("from_edit",bundle)
            NavHostFragment.findNavController(this).popBackStack()
            NavHostFragment.findNavController(this).popBackStack()
        }
        binding.btnDelete.setOnClickListener{
            viewModel.deleteTask(navArgs.id)
            val bundle = Bundle()
            bundle.putBoolean("refresh",true)
            setFragmentResult("from_details",bundle)
            val action = EditDeleteFragmentDirections.actionEditDeleteFragmentToHomeFragment()
            NavHostFragment.findNavController(this).navigate(action)
//            NavHostFragment.findNavController(this).popBackStack()
        }
    }
}