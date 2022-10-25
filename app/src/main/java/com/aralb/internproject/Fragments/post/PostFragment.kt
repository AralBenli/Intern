package com.aralb.internproject.Fragments.post


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aralb.internproject.Adapters.PostAdapter
import com.aralb.internproject.AllDatas.UserData.UserDataItem
import com.aralb.internproject.AllDatas.UsersPosts.UsersPostsItem
import com.aralb.internproject.ApiModule.MyApi
import com.aralb.internproject.R
import kotlinx.android.synthetic.main.fragment_post.*
import retrofit2.*



class PostFragment : Fragment() {
    private lateinit var user : UserDataItem
    lateinit var postAdapter: PostAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user =  requireArguments().getParcelable<UserDataItem>("user")!!


        postrecyclerview_users.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(requireContext())
        postrecyclerview_users.layoutManager = linearLayoutManager

        getPost()

    }

    private fun getPost() {

        val retrofitData = MyApi.retrofitService.getPostData(user.id)

        retrofitData.enqueue(object : Callback<List<UsersPostsItem>?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<UsersPostsItem>?>,
                response: Response<List<UsersPostsItem>?>
            ) {
                val responseBody = response.body()!!

                postAdapter = PostAdapter(requireContext(), responseBody , findNavController())
                postAdapter.notifyDataSetChanged()
                postrecyclerview_users.adapter = postAdapter
            }

            override fun onFailure(call: Call<List<UsersPostsItem>?>, t: Throwable) {
                d("MainActivity", "onFailure: " + t.message)
            }
        })
    }
}





