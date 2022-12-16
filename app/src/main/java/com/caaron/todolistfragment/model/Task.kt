package com.caaron.todolistfragment.model

data class Task(
    val id: Long? = null,
    val title: String,
    val details: String,
    val color: String?="#00E676"
)