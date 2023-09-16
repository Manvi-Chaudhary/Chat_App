package com.example.chatapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(val sender : String) : RecyclerView.Adapter<UserViewHolder>() {

    val arr : ArrayList<User> = arrayListOf()

    lateinit var mlistener : OnItemClickListener

    interface OnItemClickListener{
        fun OnItemClick(position: Int)
    }


    fun SetOnItemClickListener(listener: OnItemClickListener){
        mlistener=listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view :View = LayoutInflater.from(parent.context).inflate(R.layout.chatlist_item,parent,false)
        return UserViewHolder(view,mlistener)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newtext=arr[position].username.replaceFirst(arr[position].username[0],arr[position].username[0].uppercaseChar(),false)
        holder.text.text=newtext
        holder.subText.text=arr[position].email



    }

    override fun getItemCount(): Int {
        return arr.size
    }


    fun updateData(newArr : ArrayList<User>){
        arr.clear()
        for(i in newArr){
            if(i.username==sender){
                continue
            }
            else{
                arr.add(i)
            }
        }
        notifyDataSetChanged()
    }


}

class UserViewHolder(itemView: View,listener: UserAdapter.OnItemClickListener) : RecyclerView.ViewHolder(itemView){
    val text : TextView = itemView.findViewById(R.id.text_one)
    val subText : TextView =itemView.findViewById(R.id.text_two)
    init {
        itemView.setOnClickListener{
            listener.OnItemClick(adapterPosition)
        }
    }
}
