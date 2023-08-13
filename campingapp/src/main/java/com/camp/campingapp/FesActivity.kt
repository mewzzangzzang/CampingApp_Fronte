package com.camp.campingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.camp.campingapp.databinding.ActivityFesBinding
import com.camp.campingapp.model.FesList
import com.camp.campingapp.recycler.FesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FesActivity : AppCompatActivity() {
    lateinit var binding: ActivityFesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityFesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getFesList()
    }
    private fun getFesList(){
        val networkService = (applicationContext as MyApplication).networkService
        val fesListCall = networkService.GetFesList()

        fesListCall.enqueue(object : Callback<List<FesList>> {
            override fun onResponse(
                call: Call<List<FesList>>,
                response: Response<List<FesList>>
            ) {
                val FesList = response.body()
//                Log.d("lsy", "실행 여부 확인. userListCall.enqueue")
//                Log.d("lsy", TourList?.toString())
//                Log.d("lsy", "url:" + tourListCall.request().url().toString())

                binding.recyclerView.adapter =
                    FesAdapter(this@FesActivity,FesList)

                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(this@FesActivity, LinearLayoutManager.VERTICAL)
                )


            }

            override fun onFailure(call: Call<List<FesList>>, t: Throwable) {
                Log.d("lsy", "fail")
                call.cancel()
            }


        })
    }

}