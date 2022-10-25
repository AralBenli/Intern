package com.aralb.internproject.Fragments.photo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.aralb.internproject.Adapters.PhotoAdapter
import com.aralb.internproject.AllDatas.AlbumPhotos.AlbumPhotosItem
import com.aralb.internproject.ApiModule.MyApi
import com.aralb.internproject.R
import kotlinx.android.synthetic.main.fragment_photo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates


class PhotoFragment : Fragment() {

    lateinit var photoAdapter: PhotoAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    var albumId by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


         albumId = requireArguments().getInt("id")

        photorecyclerview_users.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(requireContext())
        photorecyclerview_users.layoutManager = linearLayoutManager

        getPhotos()

    }
    private fun getPhotos() {


        val retrofitData = MyApi.retrofitService.getPhotoData(albumId)

        retrofitData.enqueue(object : Callback<ArrayList<AlbumPhotosItem>?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ArrayList<AlbumPhotosItem>?>,
                response: Response<ArrayList<AlbumPhotosItem>?>
            ) {

                val responseBody = response.body()!!

                photoAdapter = PhotoAdapter(requireContext(), responseBody)
                photoAdapter.notifyDataSetChanged()
                photorecyclerview_users.adapter = photoAdapter
            }

            override fun onFailure(call: Call<ArrayList<AlbumPhotosItem>?>, t: Throwable) {
                d("MainActivity", "onFailure: " + t.message)
            }

        })
    } }




