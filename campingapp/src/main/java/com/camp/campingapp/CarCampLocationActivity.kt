package com.camp.campingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.camp.campingapp.carCampFragment.CarCampFragment1
import com.camp.campingapp.carCampFragment.CarCampFragment10
import com.camp.campingapp.carCampFragment.CarCampFragment11
import com.camp.campingapp.carCampFragment.CarCampFragment12
import com.camp.campingapp.carCampFragment.CarCampFragment13
import com.camp.campingapp.carCampFragment.CarCampFragment14
import com.camp.campingapp.carCampFragment.CarCampFragment15
import com.camp.campingapp.carCampFragment.CarCampFragment16
import com.camp.campingapp.carCampFragment.CarCampFragment17
import com.camp.campingapp.carCampFragment.CarCampFragment2
import com.camp.campingapp.carCampFragment.CarCampFragment3
import com.camp.campingapp.carCampFragment.CarCampFragment4
import com.camp.campingapp.carCampFragment.CarCampFragment5
import com.camp.campingapp.carCampFragment.CarCampFragment6
import com.camp.campingapp.carCampFragment.CarCampFragment7
import com.camp.campingapp.carCampFragment.CarCampFragment8
import com.camp.campingapp.carCampFragment.CarCampFragment9
import com.camp.campingapp.databinding.ActivityCarCampLocationBinding

class CarCampLocationActivity : AppCompatActivity() {

    private var currentFragment: Fragment? = null
    lateinit var binding : ActivityCarCampLocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCarCampLocationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val fragment = CarCampFragment1()
            supportFragmentManager.beginTransaction()
                .add(R.id.frameLayout, fragment)
                .commit()
        }

        binding.btnOption1.setOnClickListener {
            currentFragment = CarCampFragment1()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption2.setOnClickListener {
            currentFragment = CarCampFragment2()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption3.setOnClickListener {
            currentFragment = CarCampFragment3()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption4.setOnClickListener {
            currentFragment = CarCampFragment4()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption5.setOnClickListener {
            currentFragment = CarCampFragment5()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption6.setOnClickListener {
            currentFragment = CarCampFragment6()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption7.setOnClickListener {
            currentFragment = CarCampFragment7()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption8.setOnClickListener {
            currentFragment = CarCampFragment8()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption9.setOnClickListener {
            currentFragment = CarCampFragment9()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption10.setOnClickListener {
            currentFragment = CarCampFragment10()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption11.setOnClickListener {
            currentFragment = CarCampFragment11()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption12.setOnClickListener {
            currentFragment = CarCampFragment12()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption13.setOnClickListener {
            currentFragment = CarCampFragment13()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption14.setOnClickListener {
            currentFragment = CarCampFragment14()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption15.setOnClickListener {
            currentFragment = CarCampFragment15()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption16.setOnClickListener {
            currentFragment = CarCampFragment16()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption17.setOnClickListener {
            currentFragment = CarCampFragment17()
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