package com.camp.campingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.camp.campingapp.databinding.ActivityBoardBinding
import com.camp.campingapp.model.BoardData
import com.camp.campingapp.recycler.BoardAdapter
import com.camp.campingapp.util.myCheckPermission

class Board : AppCompatActivity() {
    private lateinit var binding: ActivityBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myCheckPermission(this)
        setupRecyclerView()

        binding.add.setOnClickListener {
            startActivity(Intent(this, BoardWrite::class.java))
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        val adapter = BoardAdapter(this, emptyList()) // 초기에 빈 목록으로 어댑터 생성
        binding.boardRecyclerView.layoutManager = layoutManager
        binding.boardRecyclerView.adapter = adapter

        // Firestore에서 데이터를 가져와서 어댑터의 데이터 갱신
        MyApplication.db.collection("Boards")
            .get()
            .addOnSuccessListener { result ->
                val itemList = result.map { document ->
                    document.toObject(BoardData::class.java).apply {
                        docId = document.id
                    }
                }
                adapter.updateData(itemList) // 어댑터의 데이터 갱신
            }
            .addOnFailureListener { exception ->
                showToast("서버 데이터 획득 실패")
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
