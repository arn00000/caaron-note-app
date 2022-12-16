package com.caaron.todolistfragment.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.caaron.todolistfragment.model.Task
import com.caaron.todolistfragment.repository.TaskRepository

class HomeViewModel(val repo: TaskRepository): ViewModel() {
    val tasks: MutableLiveData<List<Task>> = MutableLiveData()

    init{
        getTasks()

    }

    fun getTasks(){
        val res = repo.getTasks()
        tasks.value = res
    }

    class Provider(val repo:TaskRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repo) as T
        }
    }
}