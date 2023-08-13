package com.camp.campingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.camp.campingapp.databinding.ActivityCampDoNmBinding
import com.camp.campingapp.recycler.DoNmAdapter
import com.example.k0327_dum_test.model.campDoNmList
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import retrofit2.Response

class CampDoNmActivity : AppCompatActivity() {
    lateinit var binding: ActivityCampDoNmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCampDoNmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        binding.goHome.setOnClickListener {
//            val intent = Intent(this@CampDoNmActivity, MainActivity::class.java)
//            startActivity(intent)
//        }

        binding.recyclerView.setOnClickListener {
        }
        // 슬라이딩 패널 ==============================================================================
        val slidePanel = binding.mainFrame                      // SlidingUpPanel

        // 패널 열고 닫기
        binding.btnToggle.setOnClickListener {
            val state = slidePanel.panelState
            // 닫힌 상태일 경우 열기
            if (state == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                slidePanel.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            }
            // 열린 상태일 경우 닫기
            else if (state == SlidingUpPanelLayout.PanelState.EXPANDED) {
                slidePanel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            }
        }

        // 지역 코드로 캠핑장 불러오기 ==================================================================

        // 디폴트 : 강원도
        getCampDoNmList("1")
        binding.whereBtn.text = "강원도"

        binding.donm1.setOnClickListener {
            getCampDoNmList("1")
            binding.whereBtn.text = "강원도"
        }
        binding.donm2.setOnClickListener {
            getCampDoNmList("2")
            binding.whereBtn.text = "경기도"
        }
        binding.donm3.setOnClickListener {
            getCampDoNmList("3")
            binding.whereBtn.text = "경상남도"
        }
        binding.donm4.setOnClickListener {
            getCampDoNmList("4")
            binding.whereBtn.text = "경상북도"
        }
        binding.donm5.setOnClickListener {
            getCampDoNmList("5")
            binding.whereBtn.text = "대구"
        }
        binding.donm6.setOnClickListener {
            getCampDoNmList("6")
            binding.whereBtn.text = "서울"
        }
        binding.donm7.setOnClickListener {
            getCampDoNmList("7")
            binding.whereBtn.text = "인천"
        }
        binding.donm8.setOnClickListener {
            getCampDoNmList("8")
            binding.whereBtn.text = "전라남도"
        }
        binding.donm9.setOnClickListener {
            getCampDoNmList("9")
            binding.whereBtn.text = "전라북도"
        }
        binding.donm10.setOnClickListener {
            getCampDoNmList("10")
            binding.whereBtn.text = "제주도"
        }
        binding.donm11.setOnClickListener {
            getCampDoNmList("11")
            binding.whereBtn.text = "충청남도"
        }
        binding.donm12.setOnClickListener {
            getCampDoNmList("12")
            binding.whereBtn.text = "충청북도"
        }

        // ==========================================================================================
    }//onCreate 문 닫음

    private fun getCampDoNmList(donm: String) {

        var donm: String = donm
        val networkService = (applicationContext as MyApplication).networkService
        val userListCall =
            networkService.getList(donm)

        userListCall.enqueue(object : retrofit2.Callback<List<campDoNmList>> {
            override fun onResponse(
                call: retrofit2.Call<List<campDoNmList>>,
                response: Response<List<campDoNmList>>
            ) {
                val campDoNmList = response.body()

                binding.recyclerView.adapter =
                    DoNmAdapter(this@CampDoNmActivity, campDoNmList)

                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(this@CampDoNmActivity, LinearLayoutManager.VERTICAL)
                )

            }

            override fun onFailure(call: retrofit2.Call<List<campDoNmList>>, t: Throwable) {
                call.cancel()
            }
        })
    }
}