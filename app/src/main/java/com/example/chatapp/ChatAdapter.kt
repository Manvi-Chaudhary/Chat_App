package com.example.chatapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ChatAdapter(val sender1 : String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    val arr : ArrayList<Chat> = arrayListOf()
    private val TYPE_USER=1;
    private val TYPE_GUEST=2;


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_USER){
            val view :View= LayoutInflater.from(parent.context).inflate(R.layout.recitem_blue,parent,false)
            return ChatViewHolder1(view)
        }
        else{

            val view :View= LayoutInflater.from(parent.context).inflate(R.layout.recitem_grey,parent,false)
            return ChatViewHolder2(view)


        }


    }

    override fun getItemCount(): Int {
        return arr.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_USER){
            (holder as ChatViewHolder1).text.text=arr[position].message
        }else{
            (holder as ChatViewHolder2).text.text=arr[position].message
        }
    }

    fun updateData(newArr : ArrayList<Chat>){
        arr.clear()
        arr.addAll(newArr)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (arr[position].sender == sender1){
            return TYPE_USER
        }else{
            return TYPE_GUEST
        }
    }


}

class ChatViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView){
    val text : TextView = itemView.findViewById(R.id.chat1)

}

class ChatViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView){
    val text : TextView = itemView.findViewById(R.id.chat2)

}






