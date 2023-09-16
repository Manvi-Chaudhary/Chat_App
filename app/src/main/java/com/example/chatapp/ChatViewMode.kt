package com.example.chatapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ChatViewMode(application : Application) : AndroidViewModel(application) {

    val repository : ChatRepository
    init {
        val database : ChatDatabase = ChatDatabase.getDatabase(application)
        val dao : ChatDao = database.chatDao()
        val userDatabase : UserDatabase = UserDatabase.getUserDatabase(application)
        val userDao : UserDao = userDatabase.userDao()
        repository = ChatRepository(dao,userDao)
    }
    fun getAllchats(person: String,otherperson:String) : LiveData<ArrayList<Chat>>? {
        return repository.allChats(person, otherperson )
    }

    fun insertChat(chat: Chat)= viewModelScope.launch {
        repository.inserChat(chat)
    }

    fun getallUsers() : LiveData<ArrayList<User>>{
        return repository.getallUsers()
    }


    suspend fun logIn(username:String,password : String) : User? {
        return repository.logIn(username, password)

    }

    fun register(user: User) = viewModelScope.launch {
        repository.register(user)
    }

    suspend fun isUserPresent(username: String) : Boolean{
        return repository.isUserPresent(username)
    }

    fun resetPass(username: String,newPass : String) = viewModelScope.launch {
        repository.resetPass(username, newPass)
    }

    fun deleteUser()=viewModelScope.launch {
        repository.deleteUser()
    }

}