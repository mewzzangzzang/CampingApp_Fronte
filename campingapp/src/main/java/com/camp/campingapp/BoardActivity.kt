package com.camp.campingapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.camp.campingapp.databinding.ActivityBoardBinding
import com.camp.campingapp.util.myCheckPermission

class BoardActivity : AppCompatActivity() {
    lateinit var binding: ActivityBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myCheckPermission(this)


//        binding.add.setOnClickListener {
//            startActivity(Intent(this, WriteActivity::class.java))
//        }

    }
}