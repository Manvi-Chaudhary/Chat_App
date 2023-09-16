package com.example.chatapp

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Arrays

class ChatActivity : AppCompatActivity(){

    lateinit var viewMode: ChatViewMode
    val currentDateTime: java.util.Date = java.util.Date()
    lateinit var receiver : String
    lateinit var sender : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_page)
        if (intent.getStringExtra("Receiver")!=null){

            receiver=intent.getStringExtra("Receiver").toString()

        }
        if ( intent.getStringExtra("Sender")!=null){
            sender= intent.getStringExtra("Sender").toString()
        }


        val title : TextView = findViewById(R.id.chat_page_title)
        val newtext=receiver.replaceFirst(receiver[0],receiver[0].uppercaseChar(),false)
        title.text=newtext

        viewMode= ViewModelProvider(this).get(ChatViewMode::class.java)
        val recView : RecyclerView = findViewById(R.id.recChat)
        recView.layoutManager= LinearLayoutManager(this)
        val adapter : ChatAdapter = ChatAdapter(sender)
        recView.adapter=adapter

        viewMode.getAllchats(person = sender, otherperson = receiver)!!.observe(this,{
            Log.d("cahts",it.toString())
            var list=it
            var sortedList = list.sortedWith(compareBy({
                it.time
            }))
            Log.d("sorted list",sortedList.toString())
            var newList : ArrayList<Chat> = ArrayList(sortedList)
            adapter.updateData(newList)
        })

        val sendBt : ImageButton = findViewById(R.id.imageButton)

        val editText : EditText = findViewById(R.id.editTextText2)
        sendBt.setOnClickListener {
            if(editText.text.isNotEmpty()){
                viewMode.insertChat(Chat(sender,receiver,0,currentDateTime.time,editText.text.toString()))

            }
            else{
                Toast.makeText(this,"Please Enter some Text", Toast.LENGTH_SHORT).show()
            }

        }







    }
}