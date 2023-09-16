package com.example.chatapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
public abstract class UserDatabase :RoomDatabase(){

    abstract fun userDao():UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null
        fun getUserDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "User_Database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}