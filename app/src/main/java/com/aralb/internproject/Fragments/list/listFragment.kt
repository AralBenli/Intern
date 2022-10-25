package com.aralb.internproject.Fragments.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aralb.internproject.AllDatas.UserData.UserDataItem
import com.aralb.internproject.Adapters.ListAdapter
import com.aralb.internproject.ApiModule.MyApi
import com.aralb.internproject.R
import kotlinx.android.synthetic.main.listfragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response





class HomeFragment : Fragment(){
    lateinit var listAdapter: ListAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return  inflater.inflate(R.layout.listfragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview_users.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerview_users.layoutManager = linearLayoutManager

        getUsers()
    }
    private fun getUsers() {

        val retrofitData = MyApi.retrofitService.getUserData()
        retrofitData.enqueue(object : Callback<List<UserDataItem>?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<UserDataItem>?>, response: Response<List<UserDataItem>?>) {

                val responseBody = response.body()!!

                listAdapter = ListAdapter(requireContext(), responseBody , findNavController())
                listAdapter.notifyDataSetChanged()
                recyclerview_users.adapter = listAdapter

            }
            override fun onFailure(call: Call<List<UserDataItem>?>, t: Throwable) {
                d("MainActivity", "onFailure: " + t.message)
            }
        }
        )
    }
}


