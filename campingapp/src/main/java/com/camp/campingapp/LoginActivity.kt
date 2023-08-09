package com.camp.campingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.camp.campingapp.databinding.ActivityLoginBinding
import com.camp.campingapp.util.myCheckPermission

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myCheckPermission(this)
        //미디어 서버(이미지)에 접근 권한 여부를 확인하는 함수
        //매 액티비티에서 사용가능하게,파일 분리(리팩토링)
        binding.addFab.setOnClickListener {
            //인증 여부 확인.
            if(MyApplication.checkAuth()){
                //인증 완료시 AddActivity로 이동
                startActivity(Intent(this, Board::class.java))
            }else {
                //인증실패시 토스트 알림
                Toast.makeText(this, "인증진행해주세요..", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        //인증 안되면,로그아웃 버튼 보이게
        if(!MyApplication.checkAuth()){
            binding.logoutTextView.visibility= View.VISIBLE
            binding.mainRecyclerView.visibility= View.GONE
        }else {
            //반대
            binding.logoutTextView.visibility= View.GONE
            binding.mainRecyclerView.visibility= View.VISIBLE
        }
    }

    //메인 화면에,액션바에 메뉴 옵션 설정하는 코드.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //이벤트 리스터 액션바의 메뉴
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this, AuthActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

}