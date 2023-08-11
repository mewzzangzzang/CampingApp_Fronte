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
    lateinit var binding: ActivityHostListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHostListBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        myCheckPermission(this)
        binding.addFab.setOnClickListener {
//            if(MyApplication.checkAuth()){
                startActivity(Intent(this, HostListItemActivity::class.java))
//            }else {
//                Toast.makeText(this, "인증진행해주세요..", Toast.LENGTH_SHORT).show()
//            }
        }

    }

    override fun onStart() {
        super.onStart()
        if(!MyApplication.checkAuth()){
//            binding.logoutTextView.visibility= View.VISIBLE
            binding.HostRecyclerView.visibility= View.GONE
        }else {
//            binding.logoutTextView.visibility= View.GONE
            binding.HostRecyclerView.visibility= View.VISIBLE
            makeHostListRecyclerView()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this, AuthActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    private fun makeHostListRecyclerView(){
        MyApplication.db.collection("Camping")
            .get()
            .addOnSuccessListener {result ->
                val itemList = mutableListOf<HostData>()
                for(document in result){
                    val item = document.toObject(HostData::class.java)
                    item.hid=document.id
                    itemList.add(item)
                }
                binding.HostRecyclerView.layoutManager = LinearLayoutManager(this)
                binding.HostRecyclerView.adapter = HostAdapter(this, itemList)
            }
            .addOnFailureListener{exception ->
                Log.d("kkang", "error.. getting document..", exception)
                Toast.makeText(this, "서버 데이터 획득 실패", Toast.LENGTH_SHORT).show()
            }
    }
}