package com.hfad.tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.tasks.model.TaskDao

class TasksViewModelFactory(private val dao: TaskDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksViewModel::class.java))
            return TasksViewModel(dao) as T
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
/*11)Т.к. TaskViewModel содержит аргументы (val dao: TaskDao), значит
 ему нужна помощь фабрики моделей представления.*/