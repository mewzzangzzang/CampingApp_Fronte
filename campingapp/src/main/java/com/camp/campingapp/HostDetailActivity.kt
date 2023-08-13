package com.camp.campingapp

import android.os.Bundle
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

    }
}





