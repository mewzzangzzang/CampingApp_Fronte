package com.camp.campingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.camp.campingapp.databinding.ActivityCampGlampingBinding
import com.camp.campingapp.databinding.ActivityGlampingLocationBinding
import com.camp.campingapp.glampingFragment.GlampFragment1
import com.camp.campingapp.glampingFragment.GlampFragment2
import com.camp.campingapp.glampingFragment.GlampFragment10
import com.camp.campingapp.glampingFragment.GlampFragment11
import com.camp.campingapp.glampingFragment.GlampFragment12
import com.camp.campingapp.glampingFragment.GlampFragment13
import com.camp.campingapp.glampingFragment.GlampFragment14
import com.camp.campingapp.glampingFragment.GlampFragment15
import com.camp.campingapp.glampingFragment.GlampFragment16
import com.camp.campingapp.glampingFragment.GlampFragment17
import com.camp.campingapp.glampingFragment.GlampFragment3
import com.camp.campingapp.glampingFragment.GlampFragment4
import com.camp.campingapp.glampingFragment.GlampFragment5
import com.camp.campingapp.glampingFragment.GlampFragment6
import com.camp.campingapp.glampingFragment.GlampFragment7
import com.camp.campingapp.glampingFragment.GlampFragment8
import com.camp.campingapp.glampingFragment.GlampFragment9

class GlampingLocationActivity : AppCompatActivity() {

    private var currentFragment: Fragment? = null
    lateinit var binding : ActivityGlampingLocationBinding
    lateinit var binding2 : ActivityCampGlampingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityGlampingLocationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val fragment = GlampFragment1()
            supportFragmentManager.beginTransaction()
                .add(R.id.frameLayout, fragment)
                .commit()
        }

        binding.btnOption1.setOnClickListener {
            currentFragment = GlampFragment1()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption2.setOnClickListener {
            currentFragment = GlampFragment2()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption3.setOnClickListener {
            currentFragment = GlampFragment3()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption4.setOnClickListener {
            currentFragment = GlampFragment4()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption5.setOnClickListener {
            currentFragment = GlampFragment5()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption6.setOnClickListener {
            currentFragment = GlampFragment6()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption7.setOnClickListener {
            currentFragment = GlampFragment7()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption8.setOnClickListener {
            currentFragment = GlampFragment8()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption9.setOnClickListener {
            currentFragment = GlampFragment9()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption10.setOnClickListener {
            currentFragment = GlampFragment10()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption11.setOnClickListener {
            currentFragment = GlampFragment11()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption12.setOnClickListener {
            currentFragment = GlampFragment12()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption13.setOnClickListener {
            currentFragment = GlampFragment13()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption14.setOnClickListener {
            currentFragment = GlampFragment14()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption15.setOnClickListener {
            currentFragment = GlampFragment15()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption16.setOnClickListener {
            currentFragment = GlampFragment16()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption17.setOnClickListener {
            currentFragment = GlampFragment17()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }
        
    }
}