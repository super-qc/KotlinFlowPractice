package com.study.flowpractice.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class],version=1, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {

    abstract fun userDao():UserDao

    companion object{
        private var instance:AppDatabase?=null

        fun geInstance(context: Context):AppDatabase{
            return instance?: synchronized(this){
                Room.databaseBuilder(context,AppDatabase::class.java,"db_user")
                    .build().also { instance=it }
            }
        }

    }
}