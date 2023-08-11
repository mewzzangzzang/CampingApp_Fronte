package com.camp.campingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.camp.campingapp.databinding.ActivityResDetailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


class ResDetailActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore
    private lateinit var storage : FirebaseStorage

    lateinit var binding: ActivityResDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityResDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        storage = Firebase.storage

        binding

//        뒤로가기 버튼
//        binding.backResList.setOnClickListener{
//            val intent = Intent(
//                this@ResDetailActivity, ResActivity::class.java
//            )
//            startActivity(intent)
//        }

    }
}