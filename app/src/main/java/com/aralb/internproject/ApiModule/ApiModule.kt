package com.aralb.internproject.ApiModule

import com.aralb.internproject.Interfaces.ApiInterface
import com.aralb.internproject.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyApi {

    private fun retrofit(): Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    val retrofitService : ApiInterface by lazy {
        retrofit().create(ApiInterface::class.java)
    }

}

