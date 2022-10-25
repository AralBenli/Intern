package com.aralb.internproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aralb.internproject.AllDatas.UsersToDo.UsersToDoItem
import com.aralb.internproject.R
import kotlinx.android.synthetic.main.todorow_items.view.*

class TodoAdapter(val context : Context, private val userList: List<UsersToDoItem>):RecyclerView.Adapter<TodoAdapter.ViewHolder>(){


    class ViewHolder (itemView:View) :RecyclerView.ViewHolder(itemView){

        var id: TextView
        var title: TextView
        var userId: TextView
        var completed: CheckBox


        init {
            id = itemView.todoidTextView
            title = itemView.todoTitleTextView
            userId = itemView.todoUserIdTextView
            completed = itemView.todocheck
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.todorow_items,parent,false)
        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user = userList[position]

        holder.id.text=user.id.toString()
        holder.userId.text=user.userId.toString()
        holder.title.text=user.title
        holder.completed.text=user.completed.toString()
        holder.completed.isChecked=user.completed
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}