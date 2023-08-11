package com.camp.campingapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.camp.campingapp.databinding.ActivityHostDetailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


class HostDetailActivity : AppCompatActivity() {

    private lateinit var hostname: String
    private lateinit var campname: String
    private lateinit var campaddr: String
    private lateinit var camptel : String
    private lateinit var campdata: String

    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore
    private lateinit var storage : FirebaseStorage

    lateinit var binding: ActivityHostDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHostDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        storage = Firebase.storage

        binding.hostName.text = intent.getStringExtra(hostname)
        binding.campName.text = intent.getStringExtra(campname)
        binding.campAddr.text = intent.getStringExtra(campaddr)
        binding.campTel.text = intent.getStringExtra(camptel)
        binding.campIntro.text = intent.getStringExtra(campdata)

//        뒤로가기 버튼
        binding.HostList.setOnClickListener{
            val intent = Intent(
                this@HostDetailActivity, HostListActivity::class.java
            )
            startActivity(intent)
        }
        // 등록한 이미지 가져 오기
//        val imgRef = MyApplication.storage.reference.child("images/${docId}.jpg")
//        imgRef.downloadUrl.addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                Glide.with(this)
//                    .load(task.result)
//                    .into(binding.ImageView)


    }
}