package com.hfad.tasks.viewmodel

import androidx.lifecycle.ViewModel
import com.hfad.tasks.model.Task
import com.hfad.tasks.model.TaskDao

class TasksViewModel(val dao: TaskDao) : ViewModel() {
    var newTaskName = " "

    fun addTask() {
        val task = Task()
        task.taskName = newTaskName
        dao.insert(task)
    }

}
/**
 * Мы собираемся обновить код view model ,чтобы TasksFragment мог использовать его для вставки новых записей задач.
 * Чтобы сделать это, коду нужны три вещи:

1)	Ссылка на объект TaskDao
TasksViewModel будет использовать этот объект для взаимодействия с базой данных,
поэтому мы передадим его модели представления в ее конструкторе.

2)	Строковое свойство, содержащее имя новой задачи
Когда пользователь вводит новое имя задачи, TasksFragment обновит это свойство его значением.

3)	Метод AddTask(), который будет вызывать TasksFragment
Этот метод создаст новый объект Task, задаст ему имя и вставит его в базу данных, вызвав метод insert() TaskDao.
 */