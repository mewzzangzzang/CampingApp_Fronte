package com.camp.campingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.camp.campingapp.databinding.ActivityUserUpdateBinding

class UserUpdateActivity : AppCompatActivity() {

    private  lateinit var binding:ActivityUserUpdateBinding

    lateinit var sName: String
    lateinit var sPassword:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUserUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if(intent.hasExtra("username")&&intent.hasExtra("password")){
            sName=intent.getStringExtra("username")!!
            sPassword=intent.getStringExtra("password")!!
        }















    }
}