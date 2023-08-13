package com.camp.campingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.camp.campingapp.databinding.ActivityShopBinding
import com.camp.campingapp.model.ShopList
import com.camp.campingapp.recycler.ShopAdapter
import com.camp.campingapp.recycler.ShopViewHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopActivity : AppCompatActivity() {
    lateinit var binding:ActivityShopBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityShopBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getShopList()
    }
    private fun getShopList(){
        val networkService = (applicationContext as MyApplication).networkService
        val shopListCall = networkService.GetShopList()

        shopListCall.enqueue(object : Callback<List<ShopList>> {
            override fun onResponse(
                call: Call<List<ShopList>>,
                response: Response<List<ShopList>>
            ) {
                val ShopList = response.body()
//                Log.d("lsy", "실행 여부 확인. userListCall.enqueue")
//                Log.d("lsy", TourList?.toString())
//                Log.d("lsy", "url:" + tourListCall.request().url().toString())

                binding.recyclerView.adapter =
                    ShopAdapter(this@ShopActivity,ShopList)

                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(this@ShopActivity, LinearLayoutManager.VERTICAL)
                )


            }

            override fun onFailure(call: Call<List<ShopList>>, t: Throwable) {
                Log.d("lsy", "fail")
                call.cancel()
            }


        })
    }

}