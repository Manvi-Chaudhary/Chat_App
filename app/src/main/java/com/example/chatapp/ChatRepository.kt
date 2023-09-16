package com.example.chatapp

import android.util.Log
import androidx.lifecycle.LiveData

class ChatRepository (private val chatDao: ChatDao,private val userDao: UserDao) {

    fun allChats(person:String,otherperson:String) : LiveData<ArrayList<Chat>>?{
        return chatDao.getAllChat(person, otherperson) as LiveData<ArrayList<Chat>>?
    }

    suspend fun inserChat(chat: Chat){
        chatDao.insertChat(chat)
    }

    fun getallUsers() : LiveData<ArrayList<User>>{
        return userDao.getAllUsers() as LiveData<ArrayList<User>>
    }

    suspend fun register(user: User) {
        val username : String = user.username.lowercase()
        val email : String = user.email.lowercase()
        val password : String = user.password.lowercase()
        return userDao.register(User(username=username,email=email, password = password, userId = user.userId))
    }

    suspend fun logIn(username : String,password : String) : User? {
        val username1 : String = username.lowercase()
        val password1 : String = password.lowercase()
        return  userDao.logIn(username1,password1)

    }

    suspend fun isUserPresent(username: String) : Boolean{
        val username1 = username.lowercase()
        return userDao.isUsernamePresent(username)
    }

    suspend fun resetPass(username: String,newPass : String){
        val username1=username.lowercase()
        val newPass1=newPass.lowercase()
        return userDao.resetPassword(username1,newPass1)
    }

    suspend fun deleteUser(){
        return userDao.deleteUser("praveen")
    }


}