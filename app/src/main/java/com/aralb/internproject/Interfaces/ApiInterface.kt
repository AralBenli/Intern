package com.aralb.internproject.Interfaces

import com.aralb.internproject.AllDatas.AlbumPhotos.AlbumPhotosItem
import com.aralb.internproject.AllDatas.PostsComments.PostsCommentsItem
import com.aralb.internproject.AllDatas.UserData.UserDataItem
import com.aralb.internproject.AllDatas.UsersAlbum.UsersAlbumItem
import com.aralb.internproject.AllDatas.UsersPosts.UsersPostsItem
import com.aralb.internproject.AllDatas.UsersToDo.UsersToDoItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("users/")
    fun getUserData(): Call<List<UserDataItem>>

    @GET("users/{user}/albums")
    fun getAlbumData(@Path("user") id:Int): Call<List<UsersAlbumItem>>

    @GET("post/{userId}/comments")
    fun getCommendData(@Path("userId") id:Int): Call<List<PostsCommentsItem>>

    @GET("albums/{id}/photos")
    fun getPhotoData(@Path("id") id:Int): Call<ArrayList<AlbumPhotosItem>>

    @GET("users/{user}/todos")
    fun getToDoData(@Path("user") id:Int): Call<List<UsersToDoItem>>

    @GET("users/{user}/posts")
    fun getPostData(@Path("user") id:Int): Call<List<UsersPostsItem>>


}