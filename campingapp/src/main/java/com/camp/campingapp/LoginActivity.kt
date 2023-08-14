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
import com.camp.campingapp.databinding.ActivityLoginBinding
import com.camp.campingapp.model.HostData
import com.camp.campingapp.recycler.HostAdapter
import com.camp.campingapp.util.myCheckPermission

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myCheckPermission(this)
        binding.addFab.setOnClickListener {
            if(MyApplication.checkAuth()){
                startActivity(Intent(this, Board::class.java))
            }else {
                Toast.makeText(this, "로그인 인증진행해주세요..", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        if(!MyApplication.checkAuth()){
            binding.logoutTextView.visibility= View.VISIBLE
            binding.mainRecyclerView.visibility= View.GONE
        }else {
            //반대
            binding.logoutTextView.visibility= View.GONE
            binding.mainRecyclerView.visibility= View.VISIBLE
            makeRecyclerView()
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

    private fun makeRecyclerView(){
        MyApplication.db.collection("hostList")
            .get()
            .addOnSuccessListener {result ->
                val itemList = mutableListOf<HostData>()
                for(document in result){
                    val item = document.toObject(HostData::class.java)
                    item.hid=document.id
                    itemList.add(item)
                }
                binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
                binding.mainRecyclerView.adapter = HostAdapter(this@LoginActivity, itemList) { clickedItem ->}
            }
            .addOnFailureListener{exception ->
                Log.d("kkang", "error.. getting document..", exception)
                Toast.makeText(this, "LoginActivity-서버 데이터 획득 실패", Toast.LENGTH_SHORT).show()
            }
    }

}