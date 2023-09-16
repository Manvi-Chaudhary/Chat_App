package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class AllUsersActivity : AppCompatActivity() {

    lateinit var viewMode : ChatViewMode
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sender : String = intent.getStringExtra("sender").toString()

        val recView: RecyclerView = findViewById(R.id.recChatList)
        recView.layoutManager = LinearLayoutManager(this)
        val adapter: UserAdapter = UserAdapter(sender)
        recView.adapter = adapter



        viewMode= ViewModelProvider(this).get(ChatViewMode::class.java)

        viewMode.getallUsers().observe(this,{
            Log.d("allusers",it.toString())

            adapter.updateData(it)

        })

        adapter.SetOnItemClickListener(object : UserAdapter.OnItemClickListener {
            override fun OnItemClick(position: Int) {

                val intent: Intent = Intent(this@AllUsersActivity, ChatActivity::class.java)
                
                intent.putExtra("Sender", sender)
                intent.putExtra("Receiver", adapter.arr[position].username)
                startActivity(intent)
            }
        })

    }
}