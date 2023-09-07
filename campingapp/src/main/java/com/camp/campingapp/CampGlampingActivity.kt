package com.camp.campingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.camp.campingapp.databinding.ActivityCampGlampingBinding
import com.camp.campingapp.model.GlampList
import com.camp.campingapp.recycler.GlampingListAdapter
import retrofit2.Response

class CampGlampingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCampGlampingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCampGlampingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.GlampRecyclerView.setOnClickListener{
        }

        val induty = "1"
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
            val intent = Intent(this@CampGlampingActivity, GlampingLocationActivity::class.java)
            startActivity(intent)
        }

    }


    fun getGlampingList(induty:String, donm: String, sigunguNm: String) {

        Log.d("dum"," getGlampingList 실행 ${induty} ${donm} ${sigunguNm} ")

        var induty: String = induty
        var donm: String = donm
        var sigunguNm: String = sigunguNm
        val networkService = (applicationContext as MyApplication).networkService
        val userListCall =
            networkService.campList(induty, donm, sigunguNm)

        userListCall.enqueue(object : retrofit2.Callback<List<GlampList>> {
            override fun onResponse(
                call: retrofit2.Call<List<GlampList>>,
                response: Response<List<GlampList>>
            ) {
                Log.d("dum", "glampList 들어옴")
                if (response.isSuccessful) {
                    val glampList = response.body()

                    binding.GlampRecyclerView.adapter =
                        GlampingListAdapter(this@CampGlampingActivity, glampList)

                    binding.GlampRecyclerView.addItemDecoration(
                        DividerItemDecoration(this@CampGlampingActivity, LinearLayoutManager.VERTICAL)
                    )
                } else {
                    Log.e("dum", "Response not successful: ${response.code()}")
                }
            }

            override fun onFailure(call: retrofit2.Call<List<GlampList>>, t: Throwable) {
                Log.d("dum", "실패")
                call.cancel()
            }
        })
    }

}