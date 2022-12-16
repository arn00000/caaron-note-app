package com.caaron.todolistfragment.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.caaron.todolistfragment.model.Task
import com.caaron.todolistfragment.repository.TaskRepository

class AddTaskViewModel(private val repo:TaskRepository): ViewModel() {
    fun addTask(task: Task){
        repo.addTask(task)
    }

    class Provider(val repo: TaskRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddTaskViewModel(repo) as T
        }
    }

}