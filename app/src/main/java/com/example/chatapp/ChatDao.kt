package com.example.chatapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChatDao {

    @Query("SELECT * FROM Message_table WHERE sender= :person  AND receiver= :otherperson UNION SELECT * FROM Message_table WHERE sender= :otherperson  AND receiver= :person")
    fun getAllChat(person: String,otherperson : String) : LiveData<List<Chat>>?

    @Insert(entity = Chat::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertChat(chat: Chat)


}