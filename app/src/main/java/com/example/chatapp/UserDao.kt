package com.example.chatapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Query("SELECT * FROM  `User Table`")
    fun getAllUsers():LiveData<List<User>>

    @Insert(User::class,OnConflictStrategy.ABORT)
    suspend fun register(user: User)

    @Query("SELECT EXISTS(SELECT * FROM `User Table` WHERE username= :username)")
    suspend fun isUsernamePresent(username : String): Boolean

    @Query("UPDATE `User Table` SET password= :newPassword WHERE username= :username1")
    suspend fun resetPassword(username1: String,newPassword : String)

    @Query("SELECT * FROM `User Table` WHERE username LIKE :fun_username AND password LIKE :fun_password ")
    suspend fun logIn(fun_username : String,fun_password : String) : User?

    @Query("DELETE FROM `User Table` WHERE username LIKE :user")
    suspend fun deleteUser(user : String)



}