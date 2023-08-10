package com.camp.campingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.camp.campingapp.databinding.ActivityCampDoNmBinding
import com.camp.campingapp.model.campDoNmList
import com.camp.campingapp.recycler.DoNmAdapter
import retrofit2.Response

class CampDoNmActivity : AppCompatActivity() {
    lateinit var binding: ActivityCampDoNmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCampDoNmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.goHome.setOnClickListener {
            val intent = Intent(this@CampDoNmActivity, MainActivity::class.java)
            startActivity(intent)
        }

        binding.recyclerView.setOnClickListener {
            Log.d("lsy", "")
        }

        // 지역 코드로 캠핑장 불러오기 ==================================================================

        // 디폴트 : 강원도
        getCampDoNmList("1")

        binding.donm1.setOnClickListener {
            getCampDoNmList("1")
        }
        binding.donm2.setOnClickListener {
            getCampDoNmList("2")
        }
        binding.donm3.setOnClickListener {
            getCampDoNmList("3")
        }
        binding.donm4.setOnClickListener {
            getCampDoNmList("4")
        }
        binding.donm5.setOnClickListener {
            getCampDoNmList("5")
        }
        binding.donm6.setOnClickListener {
            getCampDoNmList("6")
        }
        binding.donm7.setOnClickListener {
            getCampDoNmList("7")
        }
        binding.donm8.setOnClickListener {
            getCampDoNmList("8")
        }
        binding.donm9.setOnClickListener {
            getCampDoNmList("9")
        }
        binding.donm10.setOnClickListener {
            getCampDoNmList("10")
        }
        binding.donm11.setOnClickListener {
            getCampDoNmList("11")
        }
        binding.donm12.setOnClickListener {
            getCampDoNmList("12")
        }

        // ==========================================================================================
    }//onCreate 문 닫음


    private fun getCampDoNmList(donm: String) {
//        val serviceKey =
//            "c8vC2OkkWTTNDGQwB5sEm58CgNwMvmXLZ%2BN50mqAMab74s82Vxw2VjiTBLdDxHdnzgnD%2B%2BjCobFAR9L%2FpXVSIA%3D%3D"

        var donm: String = donm
        val networkService = (applicationContext as MyApplication).networkService
        val userListCall =
            networkService.getList(donm)
        Log.d("lsy", "url:" + userListCall.request().url.toString())

        userListCall.enqueue(object : retrofit2.Callback<List<campDoNmList>> {
            override fun onResponse(
                call: retrofit2.Call<List<campDoNmList>>,
                response: Response<List<campDoNmList>>
            ) {
                val campDoNmList = response.body()
                Log.d("lsy", "실행 여부 확인. userListCall.enqueue")
                Log.d("lsy", campDoNmList?.get(0).toString())

                binding.recyclerView.adapter =
                    DoNmAdapter(this@CampDoNmActivity, campDoNmList)

                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(this@CampDoNmActivity, LinearLayoutManager.VERTICAL)
                )
                Log.d("lsy", "실행 여부 확인. userListCall.enqueue")

            }

            override fun onFailure(call: retrofit2.Call<List<campDoNmList>>, t: Throwable) {
                Log.d("lsy", "fail")
                call.cancel()
            }
        })
    }
}