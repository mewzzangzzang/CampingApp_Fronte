package com.camp.campingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.camp.campingapp.databinding.ActivityPetListBinding
import com.camp.campingapp.model.PetList
import com.camp.campingapp.recycler.PetListAdapter
import retrofit2.Response

class PetListActivity : AppCompatActivity() {


    private lateinit var binding: ActivityPetListBinding

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPetListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.setOnClickListener {
        }

        val donm = intent.getStringExtra("donm")
        val sigunguNm = intent.getStringExtra("sigunguNm")
        Log.d("dum", "인탠트로 받아온 값: getPetList called with donm: $donm, sigunguNm: $sigunguNm")
        if (donm != null && sigunguNm != null) {
            getPetList(donm, sigunguNm)
        } else {
            getPetList("1", "고성군")
        }

        binding.location.setOnClickListener {
            val intent = Intent(this@PetListActivity, PetListLocationActivity::class.java)
            startActivity(intent)
        }


    }

    fun getPetList(donm: String, sigunguNm: String) {

        Log.d("dum", "getPetList called with donm: $donm, sigunguNm: $sigunguNm")
        var donm: String = donm
        var sigunguNm: String = sigunguNm
        val networkService = (applicationContext as MyApplication).networkService
        val userListCall =
            networkService.petList(donm, sigunguNm)

        userListCall.enqueue(object : retrofit2.Callback<List<PetList>> {
            override fun onResponse(
                call: retrofit2.Call<List<PetList>>,
                response: Response<List<PetList>>
            ) {
                val petList = response.body()

                binding.recyclerView.adapter =
                    PetListAdapter(this@PetListActivity, petList)

                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(this@PetListActivity, LinearLayoutManager.VERTICAL)
                )
            }

            override fun onFailure(call: retrofit2.Call<List<PetList>>, t: Throwable) {
                call.cancel()
            }
        })
    }
}