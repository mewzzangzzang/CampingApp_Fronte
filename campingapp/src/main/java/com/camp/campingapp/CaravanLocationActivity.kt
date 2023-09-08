package com.camp.campingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.camp.campingapp.caravanFragment.CaravanFragment1
import com.camp.campingapp.caravanFragment.CaravanFragment10
import com.camp.campingapp.caravanFragment.CaravanFragment11
import com.camp.campingapp.caravanFragment.CaravanFragment12
import com.camp.campingapp.caravanFragment.CaravanFragment13
import com.camp.campingapp.caravanFragment.CaravanFragment14
import com.camp.campingapp.caravanFragment.CaravanFragment15
import com.camp.campingapp.caravanFragment.CaravanFragment16
import com.camp.campingapp.caravanFragment.CaravanFragment17
import com.camp.campingapp.caravanFragment.CaravanFragment2
import com.camp.campingapp.caravanFragment.CaravanFragment3
import com.camp.campingapp.caravanFragment.CaravanFragment4
import com.camp.campingapp.caravanFragment.CaravanFragment5
import com.camp.campingapp.caravanFragment.CaravanFragment6
import com.camp.campingapp.caravanFragment.CaravanFragment7
import com.camp.campingapp.caravanFragment.CaravanFragment8
import com.camp.campingapp.caravanFragment.CaravanFragment9
import com.camp.campingapp.databinding.ActivityCaravanLocationBinding

class CaravanLocationActivity : AppCompatActivity() {

    private var currentFragment: Fragment? = null
    lateinit var binding: ActivityCaravanLocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCaravanLocationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val fragment = CaravanFragment1()
            supportFragmentManager.beginTransaction()
                .add(R.id.frameLayout, fragment)
                .commit()
        }

        binding.btnOption1.setOnClickListener {
            currentFragment = CaravanFragment1()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption2.setOnClickListener {
            currentFragment = CaravanFragment2()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption3.setOnClickListener {
            currentFragment = CaravanFragment3()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption4.setOnClickListener {
            currentFragment = CaravanFragment4()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption5.setOnClickListener {
            currentFragment = CaravanFragment5()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption6.setOnClickListener {
            currentFragment = CaravanFragment6()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption7.setOnClickListener {
            currentFragment = CaravanFragment7()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption8.setOnClickListener {
            currentFragment = CaravanFragment8()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption9.setOnClickListener {
            currentFragment = CaravanFragment9()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption10.setOnClickListener {
            currentFragment = CaravanFragment10()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption11.setOnClickListener {
            currentFragment = CaravanFragment11()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption12.setOnClickListener {
            currentFragment = CaravanFragment12()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption13.setOnClickListener {
            currentFragment = CaravanFragment13()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption14.setOnClickListener {
            currentFragment = CaravanFragment14()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption15.setOnClickListener {
            currentFragment = CaravanFragment15()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption16.setOnClickListener {
            currentFragment = CaravanFragment16()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption17.setOnClickListener {
            currentFragment = CaravanFragment17()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

    }
}