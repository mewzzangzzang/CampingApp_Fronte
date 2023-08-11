package com.camp.campingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.camp.campingapp.databinding.ActivityHostListBinding
import com.camp.campingapp.util.myCheckPermission

class HostListActivity: AppCompatActivity() {
    lateinit var binding: ActivityHostListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHostListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myCheckPermission(this)

//        binding.add.setOnClickListener {
//            startActivity(Intent(this, WriteActivity::class.java))
//        }

    }
}