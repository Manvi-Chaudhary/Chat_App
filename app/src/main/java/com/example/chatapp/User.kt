package com.example.chatapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User Table")
data class User(val username:String,val password:String,val email : String,@PrimaryKey(autoGenerate = true)val userId:Int=0)
