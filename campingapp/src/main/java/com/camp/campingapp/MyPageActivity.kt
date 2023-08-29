package com.camp.campingapp

import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.camp.campingapp.databinding.ActivityMyPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase

class MyPageActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    lateinit var binding: ActivityMyPageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (MyApplication.checkAuth()) {
            changeVisibility("login")
        } else {
            changeVisibility("logout")
        }

        binding.userNameView.text = MyApplication.userData?.username ?: "Guest"
        binding.emailView.text=MyApplication.email


        binding.updatePasswordBtn.setOnClickListener{
            sendPasswordReset()
        }


        binding.logoutBtn.setOnClickListener {
            //로그아웃...........
            MyApplication.auth.signOut()
            MyApplication.email = null
            //이메일 널로 할당
            Toast.makeText(
                baseContext, "로그아웃 되었습니다",
                Toast.LENGTH_SHORT
            ).show()
            changeVisibility("logout")
            val intent = Intent(this@MyPageActivity, MainActivity::class.java)
            startActivity(intent)
        }

        binding.deleteButton.setOnClickListener {
            Delete()
        // ActionBar에 뒤로가기 버튼 활성화
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



    }//oncreate



    fun changeVisibility(mode: String) {
        if (mode == "loging") {
            binding.run {
                logoutBtn.visibility = View.VISIBLE
            }
        } else if (mode === "logout") {

        }

    }
}
    // ActionBar의 뒤로가기 버튼 클릭 시 호출되는 메서드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed() // 이전 화면으로 돌아가기
            return true
        }
        startActivity(Intent(this, AuthActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    fun changeVisibility(mode: String){
        if(mode === "logout") {

        } else if (mode==="login"){

        }
    }

    private fun sendPasswordReset() {
        // [START send_password_reset]
        val emailAddress = "user@example.com"

        Firebase.auth.sendPasswordResetEmail(emailAddress)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Email sent.")
                }
            }
        // [END send_password_reset]
    }

    private fun Delete(){
        val user = Firebase.auth.currentUser!!
        user.delete()
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "탈퇴 되었습니다 감사합니다", Toast.LENGTH_SHORT).show()
                }
                changeVisibility("logout")
                Toast.makeText(baseContext, "logout", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MyPageActivity, MainActivity::class.java)
                startActivity(intent)
            }
    }

    fun getUserProfile() {
        // [START get_user_profile]
        val user = Firebase.auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = it.displayName
            val email = it.email
            val photoUrl = it.photoUrl

            // Check if user's email is verified
            val emailVerified = it.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            val uid = it.uid
        }
        // [END get_user_profile]
    }
}