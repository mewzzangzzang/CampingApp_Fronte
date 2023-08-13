package com.camp.campingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.camp.campingapp.databinding.ActivityHostListBinding
import com.camp.campingapp.model.HostData
import com.camp.campingapp.recycler.HostAdapter
import com.camp.campingapp.util.myCheckPermission

class HostListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHostListBinding
    private val hostList: MutableList<HostData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHostListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView 어댑터 설정
        binding.HostRecyclerView.adapter = HostAdapter(this, hostList) { selectedHostData ->
            val intent = Intent(this, HostDetailActivity::class.java)
            intent.putExtra("data", selectedHostData)
            startActivity(intent)
        }
        binding.HostRecyclerView.layoutManager = LinearLayoutManager(this)

        // Fab 클릭 리스너 설정
        binding.addFab.setOnClickListener {
            startActivity(Intent(this, HostListItemActivity::class.java))
        }

        // 인증 체크 및 데이터 로딩
        onStart()
    }

    override fun onStart() {
        super.onStart()
        if (!MyApplication.checkAuth()) {
            binding.HostRecyclerView.visibility = View.GONE
        } else {
            binding.HostRecyclerView.visibility = View.VISIBLE
            makeHostListRecyclerView()
        }
    }

    // onCreateOptionsMenu와 onOptionsItemSelected 함수 등 다른 함수들도 여기에 위치해야 함

    private fun makeHostListRecyclerView() {
        MyApplication.db.collection("Camping")
            .get()
            .addOnSuccessListener { result ->
                val itemList = mutableListOf<HostData>()
                for (document in result) {
                    val item = document.toObject(HostData::class.java)
                    item.hid = document.id
                    itemList.add(item)
                }
                // RecyclerView 어댑터에 데이터 설정
                binding.HostRecyclerView.adapter = HostAdapter(this, itemList) { selectedHostData ->
                    val intent = Intent(this, HostDetailActivity::class.java)
                    intent.putExtra("data", selectedHostData)
                    startActivity(intent)
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "서버 데이터 획득 실패", Toast.LENGTH_SHORT).show()
            }
    }
}

