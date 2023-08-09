package com.camp.campingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.camp.campingapp.databinding.ActivityBoardDetailBinding

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
        }

        // 등록한 이미지 가져 오기
        val imgRef = MyApplication.storage.reference.child("images/${docId}.jpg")
        imgRef.downloadUrl.addOnCompleteListener{ task ->
            if(task.isSuccessful){
                Glide.with(this)
                    .load(task.result)
                    .into(binding.ImageView)
            }
        }


    }

}