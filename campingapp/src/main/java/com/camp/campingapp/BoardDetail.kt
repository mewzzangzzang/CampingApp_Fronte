package com.camp.campingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.camp.campingapp.databinding.ActivityBoardDetailBinding
import com.camp.campingapp.model.BoardData
import com.camp.campingapp.recycler.CommentAdapter
import com.google.firebase.firestore.FieldValue

class BoardDetail : AppCompatActivity() {
    lateinit var binding: ActivityBoardDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val docId = intent.getStringExtra("DocId")
        val title = intent.getStringExtra("BoardTitle")
        val content = intent.getStringExtra("BoardContent")
        val date = intent.getStringExtra("BoardDate")
//        val comment = intent.getStringExtra("comment")

//        if (docId != null) {
//            MyApplication.db.collection("Boards").document(docId).collection("Comments")
//                .get()
//                .addOnSuccessListener {result ->
//                    val commentlist = result.documents
//                    for(com in commentlist) {
//                        if(com.exists()) {
//                            Log.d("test", com.data?.get("comment").toString())
//                            //Log.d("test", com.data?.get("comment").toString())
//                        }
//                    }
//
//                    val itemList = mutableListOf<BoardData>()
//                    for(document in result){
//                        val item = document.toObject(BoardData::class.java)
//                        item.cId=document.id
//                        itemList.add(item)
//                    }
//                    binding.commentRecyclerView.layoutManager = LinearLayoutManager(this)
//                    binding.commentRecyclerView.adapter = CommentAdapter(this, itemList)
//                }
//                .addOnFailureListener{exception ->
//                    Log.d("kkang", "error.. getting document..", exception)
//                    Toast.makeText(this, "서버 데이터 획득 실패", Toast.LENGTH_SHORT).show()
//                }
//        }

//        val comment = intent.getStringExtra("comment")

        binding.BoardTitle.text = title
        binding.BoardDate.text = date
        binding.BoardContent.text = content

        // 수정
        binding.BoardModify.setOnClickListener {
            val intent = Intent(this, BoardUpdate::class.java)
            intent.putExtra("DocId", docId)
            intent.putExtra("BoardTitle", title)
            intent.putExtra("BoardContent", content)
            intent.putExtra("BoardDate", date)
            startActivity(intent)
        }

        // 삭제
        binding.BoardDelete.setOnClickListener {
            if (docId != null) {
                MyApplication.db.collection("Boards").document(docId)
                    .delete()
            }
            finish()
            overridePendingTransition(0, 0) //인텐트 효과 없애기
            val intent = intent //인텐트
            startActivity(intent) //액티비티 열기
            overridePendingTransition(0, 0) //인텐트 효과 없애기
        }

        // 등록한 이미지 가져 오기
        val imgRef = MyApplication.storage.reference.child("images/${docId}.jpg")
        imgRef.downloadUrl.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Glide.with(this)
                    .load(task.result)
                    .into(binding.ImageView)
            }
        }

        // 댓글 등록
        binding.CommentWrite.setOnClickListener {
            val commentData = mapOf(
                "comment" to binding.BoardComment.text.toString(),
                "timestamp" to FieldValue.serverTimestamp()
            )
            if (docId != null) {
                MyApplication.db.collection("Boards").document(docId)
                    .collection("Comments").add(commentData)
            }
                finish()
                overridePendingTransition(0, 0) //인텐트 효과 없애기
                val intent = intent //인텐트
                startActivity(intent) //액티비티 열기
                overridePendingTransition(0, 0) //인텐트 효과 없애기
            }
        }

    }

