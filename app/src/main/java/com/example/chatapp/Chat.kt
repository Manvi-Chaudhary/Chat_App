package com.example.chatapp

import android.content.BroadcastReceiver
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Message_table")
data class Chat(val sender : String,val receiver : String,@PrimaryKey(autoGenerate = true) val id : Int=0,val time : Long,val message: String)
