package com.caaron.todolistfragment

import android.app.Application
import com.caaron.todolistfragment.repository.TaskRepository

class MyApplication:Application() {
    val taskRepo = TaskRepository.getInstance()
}