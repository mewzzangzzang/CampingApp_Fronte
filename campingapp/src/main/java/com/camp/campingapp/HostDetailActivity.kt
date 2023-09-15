package com.camp.campingapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.camp.campingapp.databinding.ActivityHostDetailBinding
import com.camp.campingapp.model.HostData
import com.camp.campingapp.recycler.HostListAdapter
import com.google.firebase.firestore.FirebaseFirestore

class HostDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHostDetailBinding


    var db = FirebaseFirestore.getInstance()
    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 호스트 데이터 받아오는 로직
        val hostData: HostData = intent.getParcelableExtra("data")!!

        val hostDataList = listOf(hostData)
        binding.viewPager2.adapter = HostListAdapter(this, hostDataList)

    }//oncreate

    // ActionBar의 뒤로가기 버튼 클릭 시 호출되는 메서드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed() // 이전 화면으로 돌아가기
            return true
        }
        startActivity(Intent(this, MainActivity::class.java))
        return super.onOptionsItemSelected(item)
    }
}





