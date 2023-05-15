package com.hfad.tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.tasks.model.Task
import com.hfad.tasks.model.TaskDao
import kotlinx.coroutines.launch

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

class TasksViewModel(val dao: TaskDao) : ViewModel() {
    var newTaskName = " "

    //13) обновим приложение так, чтобы TasksFragment отображал список всех вставленных записей
    //13.1) используем getAll(), чтобы получить все задачи из базы данных
    val tasks = dao.getAll()


    fun addTask() {
        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            dao.insert(task)
        }
    }


}
/*10)Запустите метод insert() в фоновом режиме
 viewModelScope.launch
Это изменение означает, что каждый раз, когда вызывается метод AddTask(),
он будет использовать метод insert() TaskDao (сопрограмма) для вставки записей
в фоновом режиме.

14)Необходимо добавить свойство tasksString в TasksViewModel, а затем использовать привязку данных для отображения
его значения в макете TasksFragment. Такой подход означает, что мы сможем отображать все задачи
пользователя в текстовом представлении, которое всегда остается актуальным.*/