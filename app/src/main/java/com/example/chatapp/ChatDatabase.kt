package com.example.chatapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Chat::class), version = 1, exportSchema = false)
public abstract class ChatDatabase : RoomDatabase() {

    abstract fun chatDao() : ChatDao

    companion object{
        @Volatile
        private var INSTANCE : ChatDatabase? =null
        fun getDatabase(context:Context) : ChatDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChatDatabase::class.java,
                    "Chat_Database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }


    }

}