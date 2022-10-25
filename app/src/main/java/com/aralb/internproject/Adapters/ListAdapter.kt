package com.aralb.internproject.Adapters

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.aralb.internproject.AllDatas.UserData.UserDataItem
import com.aralb.internproject.R
import kotlinx.android.synthetic.main.row_items.view.*


class ListAdapter(val context: Context, private val userList: List<UserDataItem>,private val navController: NavController)
    :RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var email: TextView
        var name: TextView
        private var lyt_root :LinearLayout

        init {
            email = itemView.textView_email
            name = itemView.textView_name
            lyt_root = itemView.lyt_root
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.row_items,parent,false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (position % 2 == 0){
            holder.itemView.lyt_root.setBackgroundColor(Color.GRAY)
        }
        val user = userList[position]

        holder.email.text = user.email
        holder.name.text = user.name

        val bundle = Bundle()
        bundle.putParcelable("user",user)
        holder.itemView.lyt_root.setOnClickListener {
            navController.navigate(R.id.action_listFragment_to_profileFragment,bundle)
        }
    }
    override fun getItemCount(): Int {
        return userList.size
    }
}


