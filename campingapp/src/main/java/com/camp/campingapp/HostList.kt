package com.camp.campingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.camp.campingapp.databinding.ActivityBoardBinding
import com.camp.campingapp.databinding.ActivityHostListBinding
import com.camp.campingapp.model.BoardData
import com.camp.campingapp.model.HostData
import com.camp.campingapp.recycler.BoardAdapter
import com.camp.campingapp.util.myCheckPermission

class HostList : AppCompatActivity() {
    lateinit var binding: ActivityHostListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHostListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myCheckPermission(this)
        makeRecyclerView()

    }
    private fun makeRecyclerView(){
        MyApplication.db.collection("Boards")
            .get()
            .addOnSuccessListener {result ->
                val itemList = mutableListOf<HostData>()
                for(document in result){
                    val item = document.toObject(HostData::class.java)
                    item.hid=document.id
                    itemList.add(item)
                }
//                binding.hostList.View.layoutManager = LinearLayoutManager(this)
//                binding.boardRecyclerView.adapter = BoardAdapter(this, itemList)
            }
            .addOnFailureListener{exception ->
                Log.d("kkang", "error.. getting document..", exception)
                Toast.makeText(this, "서버 데이터 획득 실패", Toast.LENGTH_SHORT).show()
            }
    }

}