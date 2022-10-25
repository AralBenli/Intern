package com.aralb.internproject.Fragments.comment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.aralb.internproject.Adapters.CommentsAdapter
import com.aralb.internproject.AllDatas.PostsComments.PostsCommentsItem
import com.aralb.internproject.ApiModule.MyApi
import com.aralb.internproject.R
import kotlinx.android.synthetic.main.fragment_comment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates


class CommentFragment : Fragment() {

    private lateinit var linearLayoutManager : LinearLayoutManager
    lateinit var commentsAdapter : CommentsAdapter
    var userId by Delegates.notNull<Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         userId =  requireArguments().getInt("userId")

        commentrecyclerview_users.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(requireContext())
        commentrecyclerview_users.layoutManager = linearLayoutManager

        getComments()
    }

private fun getComments(){

        val retrofitData = MyApi.retrofitService.getCommendData(userId)

        retrofitData.enqueue(object : Callback<List<PostsCommentsItem>?> {
        @SuppressLint("NotifyDataSetChanged")
        override fun onResponse(
            call: Call<List<PostsCommentsItem>?>,
            response: Response<List<PostsCommentsItem>?>) {


            val responseBody = response.body()!!

            commentsAdapter= CommentsAdapter(requireContext(),responseBody)
            commentsAdapter.notifyDataSetChanged()
            commentrecyclerview_users.adapter=commentsAdapter

        }

        override fun onFailure(call: Call<List<PostsCommentsItem>?>, t: Throwable) {
            d("MainActivity", "onFailure: " + t.message)
        }
    })
}

}