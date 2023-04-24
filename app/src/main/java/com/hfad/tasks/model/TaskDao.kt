package com.hfad.tasks.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/*4)Сообщаем Room, что интерфейс TaskDao определяет методы доступа к данным.
Это делается путем пометки интерфейса аннотацией @Dao:*/
@Dao
interface TaskDao {
    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM task_table WHERE taskId=:taskID")
    fun get(taskID: Long): LiveData<Task>

    @Query("SELECT * FROM task_table ORDER BY taskId DESC")
    fun getAll(): LiveData<List<Task>>

}
/**Указавыем,как приложение будет получать доступ к данным таблицы,создавая аннотированный интерфейс.
 *  Этот интерфейс определяет DAO — или объект доступа к данным, — который включает в себя все методы,
 *  необходимые приложению для вставки, чтения, обновления и удаления данных.*/