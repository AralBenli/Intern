package com.aralb.internproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aralb.internproject.AllDatas.AlbumPhotos.AlbumPhotosItem
import com.aralb.internproject.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photorow_items.view.*


class PhotoAdapter(val context:Context, private val userList:ArrayList<AlbumPhotosItem>) :RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView
        var albumId: TextView
        var id : TextView
        var imageView: ImageView

        init {
            title = itemView.photoTitle
            albumId = itemView.photoAlbumId
            id = itemView.photoid
            imageView = itemView.photoImageView

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.photorow_items,parent,false)
        return ViewHolder(itemView)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text=userList[position].title
        holder.albumId.text=userList[position].albumId.toString()
        holder.id.text=userList[position].id.toString()

       Picasso.get()
            .load(userList[position].url)
            .into(holder.imageView)

       /*Glide.with(context)
            .load(userList[position].url+".png")
            .into(holder.imageView)*/

        /*val url = GlideUrl(
            userList[position].url, LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build())*/



    }

    override fun getItemCount(): Int {
        return userList.size
    }
}



