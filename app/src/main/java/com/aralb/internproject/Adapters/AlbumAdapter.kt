package com.aralb.internproject.Adapters

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.aralb.internproject.AllDatas.UsersAlbum.UsersAlbumItem
import com.aralb.internproject.R
import kotlinx.android.synthetic.main.albumrow_items.view.*


class AlbumAdapter (val context : Context, private val userAlbumList: List<UsersAlbumItem> , private val navController: NavController) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>(){
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        var id: TextView
        var title: TextView
        var userId: TextView


        init {
            id = itemView.albumidTextView
            title = itemView.albumTitleTextView
            userId = itemView.albumuserIdTextView

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.albumrow_items,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {

        if (position % 2 == 0){
            holder.itemView.album_layout.setBackgroundColor(Color.GRAY)
        }

        val album = userAlbumList[position]

        holder.id.text=album.id.toString()
        holder.title.text=album.title
        holder.userId.text=album.userId.toString()

        val bundle = Bundle()
        bundle.putInt("id",album.id)
        holder.itemView.album_layout.setOnClickListener {
            navController.navigate(R.id.action_albumFragment_to_photoFragment , bundle)
        }

    }
    override fun getItemCount(): Int {
        return userAlbumList.size
    }

}