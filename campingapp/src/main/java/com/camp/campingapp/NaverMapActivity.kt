package com.camp.campingapp

import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.camp.campingapp.model.AddrList
import com.camp.campingapp.recycler.MyAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NaverMapActivity : AppCompatActivity() {
    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null
    lateinit var mLastLocation: Location
    val _type:"json"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_naver_map)
    }
    val networkService = (applicationContext as MyApplication).networkService

    val gpsListCall = networkService.getAddGps(mLastLocation,_type)
//    Log.d("lsy", "url:" + userListCall.request().url().toString())


    gpsListCall.enqueue(object : Callback<AddrList> {
        override fun onResponse(
            call: Call<AddrList>,
            response: Response<AddrList>
        ) {

            val userList = response.body()
            Log.d("lsy","${userList}")
            //Log.d("lsy", "userList data 값 : ${userList?.GetCamp?.item}")
//            Log.d("lsy", userList?.response?.body?.items?.item?.get(0)?.facltNm.toString())
//            binding.recyclerView.adapter =
//                MyAdapter(this@ListActivity, userList?.response?.body?.items?.item)
//
//            binding.recyclerView.addItemDecoration(
//                DividerItemDecoration(this@ListActivity, LinearLayoutManager.VERTICAL)
//            )

        }

        override fun onFailure(call: retrofit2.Call<AddrList>, t: Throwable) {
            Log.d("lsy", "fail")
            call.cancel()
        }
    })

}






