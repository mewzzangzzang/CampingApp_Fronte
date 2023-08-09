package com.camp.campingapp

import android.app.Application
import com.camp.campingapp.retrofit.NetworkServiceDoNm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application(){

    //add....................................
    var networkService: NetworkServiceDoNm

    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("http://10.100.103.43:8083/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    init {
        networkService = retrofit.create(NetworkServiceDoNm::class.java)
    }
}