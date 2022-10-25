package com.aralb.internproject.Fragments.album

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aralb.internproject.Adapters.AlbumAdapter
import com.aralb.internproject.AllDatas.UserData.UserDataItem
import com.aralb.internproject.AllDatas.UsersAlbum.UsersAlbumItem
import com.aralb.internproject.ApiModule.MyApi
import com.aralb.internproject.R
import kotlinx.android.synthetic.main.fragment_album.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AlbumFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var albumAdapter: AlbumAdapter
    lateinit var user : UserDataItem


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user =  requireArguments().getParcelable<UserDataItem>("user")!!



        albumrecyclerview_users.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(requireContext())
        albumrecyclerview_users.layoutManager = linearLayoutManager

        getUserAlbums()

    }
    private fun getUserAlbums(){

        val retrofitData = MyApi.retrofitService.getAlbumData(user.id)

        retrofitData.enqueue(object : Callback<List<UsersAlbumItem>?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<UsersAlbumItem>?>,
                response: Response<List<UsersAlbumItem>?>) {

                val responseBody = response.body()!!

                albumAdapter = AlbumAdapter(requireContext(),responseBody ,findNavController())
                albumAdapter.notifyDataSetChanged()
                albumrecyclerview_users.adapter=albumAdapter

            }
            override fun onFailure(call: Call<List<UsersAlbumItem>?>, t: Throwable) {
                d("MainActivity", "onFailure: " + t.message)
            }
        })
    }
}