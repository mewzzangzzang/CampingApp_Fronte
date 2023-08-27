package com.camp.campingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.camp.campingapp.databinding.ActivityMyPageBinding
import com.google.firebase.auth.FirebaseAuth

class MyPageActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    lateinit var binding:ActivityMyPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if(MyApplication.checkAuth()){
            changeVisibility("login")
        }else {
            changeVisibility("logout")
        }

        binding.logoutBtn.setOnClickListener {
            //로그아웃...........
            MyApplication.auth.signOut()
            MyApplication.email = null
            //이메일 널로 할당
            Toast.makeText(baseContext,"로그아웃 되었습니다",
                Toast.LENGTH_SHORT).show()
            changeVisibility("logout")
            val intent=Intent(this@MyPageActivity,MainActivity::class.java)
            startActivity(intent)
        }

    }

    fun changeVisibility(mode:String){
        if(mode=="loging"){
            binding.run {
                logoutBtn.visibility=View.VISIBLE
            }
        }else if(mode==="logout"){

        }

    }
}