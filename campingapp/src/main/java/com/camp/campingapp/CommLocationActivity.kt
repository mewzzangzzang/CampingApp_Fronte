package com.camp.campingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.camp.campingapp.commFragment.CommFragment1
import com.camp.campingapp.commFragment.CommFragment10
import com.camp.campingapp.commFragment.CommFragment11
import com.camp.campingapp.commFragment.CommFragment12
import com.camp.campingapp.commFragment.CommFragment13
import com.camp.campingapp.commFragment.CommFragment14
import com.camp.campingapp.commFragment.CommFragment15
import com.camp.campingapp.commFragment.CommFragment16
import com.camp.campingapp.commFragment.CommFragment17
import com.camp.campingapp.commFragment.CommFragment2
import com.camp.campingapp.commFragment.CommFragment3
import com.camp.campingapp.commFragment.CommFragment4
import com.camp.campingapp.commFragment.CommFragment5
import com.camp.campingapp.commFragment.CommFragment6
import com.camp.campingapp.commFragment.CommFragment7
import com.camp.campingapp.commFragment.CommFragment8
import com.camp.campingapp.commFragment.CommFragment9
import com.camp.campingapp.databinding.ActivityCommLocationBinding

class CommLocationActivity : AppCompatActivity() {

    private var currentFragment: Fragment? = null
    lateinit var binding: ActivityCommLocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCommLocationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            val fragment = CommFragment1()
            supportFragmentManager.beginTransaction()
                .add(R.id.frameLayout, fragment)
                .commit()
        }

        binding.btnOption1.setOnClickListener {
            currentFragment = CommFragment1()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption2.setOnClickListener {
            currentFragment = CommFragment2()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption3.setOnClickListener {
            currentFragment = CommFragment3()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption4.setOnClickListener {
            currentFragment = CommFragment4()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption5.setOnClickListener {
            currentFragment = CommFragment5()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption6.setOnClickListener {
            currentFragment = CommFragment6()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption7.setOnClickListener {
            currentFragment = CommFragment7()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption8.setOnClickListener {
            currentFragment = CommFragment8()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption9.setOnClickListener {
            currentFragment = CommFragment9()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption10.setOnClickListener {
            currentFragment = CommFragment10()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption11.setOnClickListener {
            currentFragment = CommFragment11()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption12.setOnClickListener {
            currentFragment = CommFragment12()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption13.setOnClickListener {
            currentFragment = CommFragment13()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption14.setOnClickListener {
            currentFragment = CommFragment14()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption15.setOnClickListener {
            currentFragment = CommFragment15()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption16.setOnClickListener {
            currentFragment = CommFragment16()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption17.setOnClickListener {
            currentFragment = CommFragment17()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

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