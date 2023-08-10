package com.camp.campingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.camp.campingapp.databinding.ActivityTourBinding
import com.camp.campingapp.recycler.TourAdapter
import com.camp.campingapp.model.TourList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TourActivity : AppCompatActivity() {
    lateinit var binding: ActivityTourBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityTourBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getTourList()


    }//onCreate 문 닫음


    private fun getTourList() {

        val networkService = (applicationContext as MyApplication).networkService
        val tourListCall = networkService.GetTourList()


       tourListCall.enqueue(object : Callback <List<TourList>> {
            override fun onResponse(
                call: Call<List<TourList>>,
                response: Response<List<TourList>>
            ) {
                val tourList = response.body()
//                Log.d("lsy", "실행 여부 확인. userListCall.enqueue")
//                Log.d("lsy", TourList?.toString())
//                Log.d("lsy", "url:" + tourListCall.request().url().toString())

                binding.recyclerView.adapter =
                    TourAdapter(this@TourActivity,tourList)

                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(this@TourActivity, LinearLayoutManager.VERTICAL)
                )


            }

            override fun onFailure(call: Call<List<TourList>>, t: Throwable) {
                Log.d("lsy", "fail")
                call.cancel()
            }


       })
    }

}




