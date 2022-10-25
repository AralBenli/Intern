package com.aralb.internproject.Fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.aralb.internproject.AllDatas.UserData.UserDataItem
import com.aralb.internproject.R
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment()  {

lateinit var user: UserDataItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user = requireArguments().getParcelable<UserDataItem>("user")!!
        txtName.text = user.name

        val bundle = Bundle()
        bundle.putParcelable("user",user)

        view.buttonAlbum.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_albumFragment,bundle)
        }
        view.buttonPosts.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_postFragment, bundle)
        }
        view.buttonTodo.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_toDoFragment, bundle)
        }




    }
}
