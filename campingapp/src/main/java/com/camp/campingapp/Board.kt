package com.camp.campingapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.camp.campingapp.databinding.ActivityBoardBinding
import com.camp.campingapp.model.BoardData
import com.camp.campingapp.recycler.BoardAdapter
import com.camp.campingapp.util.myCheckPermission

class Board : AppCompatActivity() {
    private lateinit var binding: ActivityBoardBinding
    private val REQUEST_CODE_ADD_BOARD = 123 // 임의의 요청 코드

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myCheckPermission(this)

        // ActionBar에 뒤로가기 버튼 활성화
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 검색 레이아웃 기본 가시성 설정
        binding.searchLayout.visibility = View.VISIBLE

        // Firestore에서 데이터를 가져와서 어댑터의 데이터 갱신 후에 setupRecyclerView() 호출
        MyApplication.db.collection("Boards")
            .get()
            .addOnSuccessListener { result ->
                val itemList = result.map { document ->
                    document.toObject(BoardData::class.java).apply {
                        docId = document.id
                    }
                }
                val adapter = BoardAdapter(this, itemList)
                binding.boardRecyclerView.adapter = adapter

                setupRecyclerView() // setupRecyclerView() 호출 위치 변경
            }
            .addOnFailureListener { exception ->
                showToast("서버 데이터 획득 실패")
            }

        binding.searchButton.setOnClickListener {
            val query = binding.searchEditText.text.toString()
            (binding.boardRecyclerView.adapter as BoardAdapter).filter(query)
        }

        binding.add.setOnClickListener {
            startActivityForResult(Intent(this, BoardWrite::class.java), REQUEST_CODE_ADD_BOARD)
        }
    }

    // ActionBar의 뒤로가기 버튼 클릭 시 호출되는 메서드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // 이전 화면으로 돌아가기
                true
            }
            else -> super.onOptionsItemSelected(item)
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

    // 결과 코드 처리
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_BOARD && resultCode == Activity.RESULT_OK) {
            // 게시글 리스트를 다시 가져와서 어댑터 업데이트
            MyApplication.db.collection("Boards")
                .get()
                .addOnSuccessListener { result ->
                    val itemList = result.map { document ->
                        document.toObject(BoardData::class.java).apply {
                            docId = document.id
                        }
                    }
                    val adapter = binding.boardRecyclerView.adapter as BoardAdapter
                    adapter.updateData(itemList) // 어댑터의 데이터 갱신
                }
                .addOnFailureListener { exception ->
                    showToast("서버 데이터 획득 실패")
                }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
