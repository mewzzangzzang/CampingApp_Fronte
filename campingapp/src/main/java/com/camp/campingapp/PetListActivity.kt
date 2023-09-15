package com.camp.campingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.camp.campingapp.databinding.ActivityPetListBinding
import com.camp.campingapp.model.PetList
import com.camp.campingapp.recycler.PetListAdapter
import retrofit2.Response

class PetListActivity : AppCompatActivity() {


    private lateinit var binding: ActivityPetListBinding

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPetListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.setOnClickListener {
        }

        val donm = intent.getStringExtra("donm")
        val sigunguNm = intent.getStringExtra("sigunguNm")
        if (donm != null && sigunguNm != null) {
            getPetList(donm, sigunguNm)
            binding.where.text = sigunguNm
        } else {
            getPetList("1", "강릉시")
            binding.where.text = "강릉시"
        }


        binding.location.setOnClickListener {
            val intent = Intent(this@PetListActivity, PetListLocationActivity::class.java)
            startActivity(intent)
        }


        // ActionBar에 뒤로가기 버튼 활성화
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    // ActionBar의 뒤로가기 버튼 클릭 시 호출되는 메서드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed() // 이전 화면으로 돌아가기
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun getPetList(donm: String, sigunguNm: String) {

        var donm: String = donm
        var sigunguNm: String = sigunguNm
        val networkService = (applicationContext as MyApplication).networkService
        val userListCall =
            networkService.petList(donm, sigunguNm)

        userListCall.enqueue(object : retrofit2.Callback<List<PetList>> {
            override fun onResponse(
                call: retrofit2.Call<List<PetList>>,
                response: Response<List<PetList>>
            ) {
                val petList = response.body()

                binding.recyclerView.adapter =
                    PetListAdapter(this@PetListActivity, petList)

                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(this@PetListActivity, LinearLayoutManager.VERTICAL)
                )
            }

            override fun onFailure(call: retrofit2.Call<List<PetList>>, t: Throwable) {
                call.cancel()
            }
        })
    }
}