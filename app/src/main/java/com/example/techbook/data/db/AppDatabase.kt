package com.example.techbook.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.techbook.data.db.dao.CircleDao
import com.example.techbook.data.db.entity.CircleDB

@Database(entities = [CircleDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    // DAOを取得する。
    abstract fun userDao(): CircleDao

    companion object{
        private  var instance: AppDatabase? = null

        fun get(context: Context): AppDatabase {
            val instance = instance
            if( instance != null){ return instance }
            val new_instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "circledb").build()
            Companion.instance = new_instance
            return new_instance
        }
    }
}
