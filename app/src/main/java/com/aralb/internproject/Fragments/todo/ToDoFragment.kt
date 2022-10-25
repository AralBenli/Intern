package com.aralb.internproject.Fragments.todo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.aralb.internproject.Adapters.TodoAdapter
import com.aralb.internproject.AllDatas.UserData.UserDataItem
import com.aralb.internproject.AllDatas.UsersToDo.UsersToDoItem
import com.aralb.internproject.ApiModule.MyApi
import com.aralb.internproject.R
import kotlinx.android.synthetic.main.fragment_to_do.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class ToDoFragment : Fragment() {
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var todoAdapter: TodoAdapter
    lateinit var user : UserDataItem


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user =  requireArguments().getParcelable<UserDataItem>("user")!!

        todorecyclerview_users.setHasFixedSize(true)
        linearLayoutManager= LinearLayoutManager(requireContext())
        todorecyclerview_users.layoutManager=linearLayoutManager

        getUsersToDo()
    }

    private fun getUsersToDo(){

        val retrofitData = MyApi.retrofitService.getToDoData(user.id)

        retrofitData.enqueue(object : Callback<List<UsersToDoItem>?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<UsersToDoItem>?>,
                response: Response<List<UsersToDoItem>?>
            ) {
                val responseBody = response.body()!!

                todoAdapter= TodoAdapter(requireContext(),responseBody)
                todoAdapter.notifyDataSetChanged()
                todorecyclerview_users.adapter=todoAdapter

            }

            override fun onFailure(call: Call<List<UsersToDoItem>?>, t: Throwable) {
                d("Main Activity" ,"onFailure"+ t.message)
            }
        })
    }

}