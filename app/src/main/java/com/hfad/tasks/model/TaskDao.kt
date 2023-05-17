package com.hfad.tasks.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**Указавыем,как приложение будет получать доступ к данным таблицы,создавая аннотированный интерфейс.
 *  Этот интерфейс определяет DAO — или объект доступа к данным, — который включает в себя все методы,
 *  необходимые приложению для вставки, чтения, обновления и удаления данных.*/
/*4)Сообщаем Room, что интерфейс TaskDao определяет методы доступа к данным.
Это делается путем пометки интерфейса аннотацией @Dao:*/
@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM task_table WHERE taskId=:key")
    fun get(key: Long): LiveData<Task>

    @Query("SELECT * FROM task_table ORDER BY taskId DESC")
    fun getAll(): LiveData<List<Task>>

}
/*9)Отметьте каждый из методов доступа к данным DAO с помощью suspend.
Это превращает каждый метод в сопрограмму (Coroutines),
которая выполняется в фоновом режиме и может быть приостановлена*/
