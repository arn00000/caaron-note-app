package com.caaron.todolistfragment.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.caaron.todolistfragment.model.Task
import com.caaron.todolistfragment.repository.TaskRepository

class DetailsViewModel(private val repo: TaskRepository) : ViewModel() {
    val task: MutableLiveData<Task> = MutableLiveData()

    fun getTaskById(id: Long) {
        val res = repo.getTaskById(id)
        res?.let {
            task.value = it
        }
    }

    fun deleteTask(id:Long){
        repo.deleteTask(id)
    }

    class Provider(val repo: TaskRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailsViewModel(repo) as T
        }
    }
}