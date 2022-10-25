package com.aralb.internproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aralb.internproject.AllDatas.PostsComments.PostsCommentsItem
import com.aralb.internproject.R
import kotlinx.android.synthetic.main.commentsrow_items.view.*

class CommentsAdapter(val context : Context, private val userList: List<PostsCommentsItem>):RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {
    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView) {

        var postId: TextView
        var id: TextView
        var name: TextView
        var email: TextView
        var body: TextView

        init {
            postId = itemView.commentsPostIdTextView
            id = itemView.commentsidTextView
            name = itemView.commentsNameTextView
            email = itemView.commentsEmailTextView
            body = itemView.commentsBodyTextView}


        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.commentsrow_items,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.postId.text=userList[position].postId.toString()
        holder.id.text=userList[position].id.toString()
        holder.name.text=userList[position].name
        holder.email.text=userList[position].email
        holder.body.text=userList[position].body

    }

    override fun getItemCount(): Int {
        return userList.size
    }
}