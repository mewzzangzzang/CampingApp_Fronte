package com.camp.campingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
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
        binding = ActivityFesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getFesList()


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
    private fun getFesList(){
        val networkService = (applicationContext as MyApplication).networkService
        val fesListCall = networkService.GetFesList()

        fesListCall.enqueue(object : Callback<List<FesList>> {
            override fun onResponse(
                call: Call<List<FesList>>,
                response: Response<List<FesList>>
            ) {
                val FesList = response.body()

                binding.recyclerView.adapter =
                    FesAdapter(this@FesActivity,FesList)

                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(this@FesActivity, LinearLayoutManager.VERTICAL)
                )


            }

            override fun onFailure(call: Call<List<FesList>>, t: Throwable) {
                call.cancel()
            }


        })
    }

}