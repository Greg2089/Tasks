package com.hfad.tasks.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//1) Добавляя аннотацию @Entity к классу данных, указываем имя таблицы.
@Entity(tableName = "task_table")
data class Task(
    /*2) Далее мы укажем первичный ключ таблицы. Это используется для уникальной идентификации
    одной записи и не может содержать никаких повторяющихся значений.
   В приложении Tasks мы будем использовать свойство TaskId для первичного ключа task_table
   и заставим таблицу автоматически генерировать свои значения, чтобы они были уникальными.
   Это делается с помощью аннотации @PrimaryKey.   */

    @PrimaryKey(autoGenerate = true)
    var taskId: Long = 0L,
    /*3) Укажем имена столбцов для свойств TaskName и taskDone.Аннотация @ColumnInfo необходима
    только в том случае, если вы хотите, чтобы имя столбца отличалось от имени свойства. */
    @ColumnInfo(name = "task_name")
    var taskName: String = "",
    @ColumnInfo(name = "task_done")
    var taskDone: Boolean = false
)
/**
 * Настройка Room!!!
 * I) 1,2,3 шаги. Room использует этот файл для создания таблицы с именем task_table с автоматически
 * сгенерированным первичным ключом с именем TaskId и двумя дополнительными столбцами с именами task_name и task_done.
 * */