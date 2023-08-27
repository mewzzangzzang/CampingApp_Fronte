package com.camp.campingapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.camp.campingapp.databinding.ActivityChatMainBinding
import com.camp.campingapp.model.User
import com.camp.campingapp.recycler.UserAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ChatMainActivity : AppCompatActivity() {

    lateinit var binding: ActivityChatMainBinding
    lateinit var adapter: UserAdapter
    private lateinit var userList: ArrayList<User>
    private lateinit var auth: FirebaseAuth
    private lateinit var rdb: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        rdb = Firebase.database.reference
        userList = ArrayList()

        adapter = UserAdapter(this, userList)
        binding.userRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.userRecyclerView.itemAnimator = DefaultItemAnimator()
        binding.userRecyclerView.adapter = adapter

        rdb.child("user").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(User::class.java)
                    if (auth.currentUser?.uid != currentUser?.uid) {
                        userList.add(currentUser!!)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // 실패 시 실행
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.log_out) {
            auth.signOut()
            startActivity(Intent(this@ChatMainActivity, LoginActivity::class.java))
            finish()
            return true
        }
        return true
    }
}