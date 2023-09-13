package com.camp.campingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.camp.campingapp.databinding.ActivityPetListBinding
import com.camp.campingapp.databinding.ActivityPetListLocationBinding
import com.camp.campingapp.model.PetList
import com.camp.campingapp.petFragment.PetFragment1
import com.camp.campingapp.petFragment.PetFragment10
import com.camp.campingapp.petFragment.PetFragment11
import com.camp.campingapp.petFragment.PetFragment12
import com.camp.campingapp.petFragment.PetFragment13
import com.camp.campingapp.petFragment.PetFragment14
import com.camp.campingapp.petFragment.PetFragment15
import com.camp.campingapp.petFragment.PetFragment16
import com.camp.campingapp.petFragment.PetFragment17
import com.camp.campingapp.petFragment.PetFragment2
import com.camp.campingapp.petFragment.PetFragment3
import com.camp.campingapp.petFragment.PetFragment4
import com.camp.campingapp.petFragment.PetFragment5
import com.camp.campingapp.petFragment.PetFragment6
import com.camp.campingapp.petFragment.PetFragment7
import com.camp.campingapp.petFragment.PetFragment8
import com.camp.campingapp.petFragment.PetFragment9
import com.camp.campingapp.recycler.PetListAdapter
import retrofit2.Response

class PetListLocationActivity : AppCompatActivity() {

    private var currentFragment: Fragment? = null
    lateinit var binding: ActivityPetListLocationBinding
    lateinit var binding2: ActivityPetListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPetListLocationBinding.inflate(layoutInflater)
        binding2 = ActivityPetListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 처음 표시할 프래그먼트 설정
        if (savedInstanceState == null) {
            val fragment = PetFragment1()
            supportFragmentManager.beginTransaction()
                .add(R.id.frameLayout, fragment)
                .commit()
        }

        binding.btnOption1.setOnClickListener {
            currentFragment = PetFragment1()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption2.setOnClickListener {
            currentFragment = PetFragment2()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption3.setOnClickListener {
            currentFragment = PetFragment3()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption4.setOnClickListener {
            currentFragment = PetFragment4()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption5.setOnClickListener {
            currentFragment = PetFragment5()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption6.setOnClickListener {
            currentFragment = PetFragment6()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption7.setOnClickListener {
            currentFragment = PetFragment7()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption8.setOnClickListener {
            currentFragment = PetFragment8()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption9.setOnClickListener {
            currentFragment = PetFragment9()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption10.setOnClickListener {
            currentFragment = PetFragment10()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption11.setOnClickListener {
            currentFragment = PetFragment11()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption12.setOnClickListener {
            currentFragment = PetFragment12()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption13.setOnClickListener {
            currentFragment = PetFragment13()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption14.setOnClickListener {
            currentFragment = PetFragment14()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption15.setOnClickListener {
            currentFragment = PetFragment15()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption16.setOnClickListener {
            currentFragment = PetFragment16()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, currentFragment!!)
                .commit()
        }

        binding.btnOption17.setOnClickListener {
            currentFragment = PetFragment17()
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
