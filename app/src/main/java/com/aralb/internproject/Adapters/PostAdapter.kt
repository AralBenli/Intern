package com.aralb.internproject.Adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.aralb.internproject.AllDatas.UsersPosts.UsersPostsItem
import com.aralb.internproject.R
import kotlinx.android.synthetic.main.postrow_items.view.*


class PostAdapter(val context: Context, private val userPostList: List<UsersPostsItem> , private val navController: NavController): RecyclerView.Adapter<PostAdapter.ViewHolder>() {
        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

            var userId: TextView
            var title: TextView
            var body: TextView
            private var post_layout:LinearLayout

            init {
                userId = itemView.postidTextView
                title = itemView.postTitleTextView
                body = itemView.postBodyTextView
                post_layout = itemView.post_layout
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.postrow_items,parent,false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



        val post = userPostList[position]

        holder.title.text  = post.title
        holder.body.text   = post.body
        holder.userId.text = post.userId.toString()


        val bundle = Bundle()
        bundle.putInt("userId",post.userId)
        holder.itemView.post_layout.setOnClickListener {
            navController.navigate(R.id.action_postFragment_to_commentFragment , bundle)
        }


    }

    override fun getItemCount(): Int {
        return userPostList.size
    }
}