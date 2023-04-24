package com.hfad.tasks.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//1) Помечаем класс с помощью @Database, которая сообщает Room, что он определяет базу данных.
@Database(entities = [Task::class], version = 1, exportSchema = false)
//Класс TaskDatabase должен расширять RoomDatabase
abstract class TaskDatabase : RoomDatabase() {
    /*2) Указываю любые интерфейсы (помеченные @Dao), которые будут использоваться для доступа к данным.

    Это делается путем добавления свойства для каждого интерфейса.
    Например, в приложении Tasks мы определили единый интерфейс DAO с именем TaskDao,
    поэтому нам нужно добавить новое свойство taskDao в код базы данных TaskDatabase следующим образом:
    */
    abstract val taskDao: TaskDao
   /*3)Создаем и везвращаем экземпляр базы данных - database
 метод getInstance(), который создаст базу данных и вернет ее экземпляр.Код выглядит следующим образом:
*/
    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getInstence(context: Context): TaskDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        "task_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
/**Определяем базу данных приложения, создавая абстрактный класс. Абстрактный класс определяет
 * имя базы данных и номер версии, а также любые классы или интерфейсы, которые определяют таблицы и
 * методы доступа к данным.
 * *//* entities указывает любые классы, помеченные @Entity, которые определяют таблицы,
которые вы хотите добавить в базу данных. Для приложения Tasks это класс данных задачи.

version - это значение Int, указывающее версию базы данных.
В данном случае это 1, так как это первая версия базы данных.

exportSchema сообщает Room, следует ли экспортировать схему базы данных в папку,
чтобы вы могли записать историю ее версий. Здесь мы устанавливаем для него значение false.
*/