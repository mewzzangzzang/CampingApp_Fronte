package com.camp.campingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.camp.campingapp.databinding.ActivityCampCarBinding
import com.camp.campingapp.model.GlampList
import com.camp.campingapp.recycler.CarCampListAdapter
import com.camp.campingapp.recycler.CommListAdapter
import retrofit2.Response

class CampCarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCampCarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCampCarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.CarCampRecyclerView.setOnClickListener{
        }

        val induty = "3"
        val donm = intent.getStringExtra("donm")
        val sigunguNm = intent.getStringExtra("sigunguNm")

        if (donm != null && sigunguNm != null) {
            getGlampingList(induty,donm, sigunguNm)
            binding.where.text = sigunguNm
        } else {
            getGlampingList(induty,"1", "강릉시")
            binding.where.text = "강릉시"
        }

        binding.location.setOnClickListener {
            val intent = Intent(this@CampCarActivity, CarCampLocationActivity::class.java)
            startActivity(intent)
        }

    }

    fun getGlampingList(induty:String, donm: String, sigunguNm: String) {

        Log.d("dum"," getCarCampList 실행 ${induty} ${donm} ${sigunguNm} ")

        var induty: String = induty
        var donm: String = donm
        var sigunguNm: String = sigunguNm
        val networkService = (applicationContext as MyApplication).networkService
        val userListCall =
            networkService.campList(induty, donm, sigunguNm)
        Log.d("dum","${induty} ${donm} ${sigunguNm}")

        userListCall.enqueue(object : retrofit2.Callback<List<GlampList>> {
            override fun onResponse(
                call: retrofit2.Call<List<GlampList>>,
                response: Response<List<GlampList>>
            ) {
                    val glampList = response.body()

                    binding.CarCampRecyclerView.adapter =
                        CarCampListAdapter(this@CampCarActivity, glampList)

                    binding.CarCampRecyclerView.addItemDecoration(
                        DividerItemDecoration(this@CampCarActivity, LinearLayoutManager.VERTICAL)
                    )
            }

            override fun onFailure(call: retrofit2.Call<List<GlampList>>, t: Throwable) {
                Log.d("dum", "실패")
                call.cancel()
            }
        })
    }

}