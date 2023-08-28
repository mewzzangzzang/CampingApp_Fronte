package com.camp.campingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.camp.campingapp.databinding.ActivityPetListBinding
import com.camp.campingapp.model.PetList
import com.camp.campingapp.recycler.PetListAdapter
import retrofit2.Response

class PetListActivity : AppCompatActivity() {
    lateinit var binding: ActivityPetListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPetListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.setOnClickListener {
        }
        getPetList("1","강릉시")
    }

    private fun getPetList(donm: String,sigunguNm:String) {

        var donm: String = donm
        var sigunguNm: String = sigunguNm
        val networkService = (applicationContext as MyApplication).networkService
        val userListCall =
            networkService.petList(donm,sigunguNm)

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