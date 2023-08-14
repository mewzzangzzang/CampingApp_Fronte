package com.camp.campingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.camp.campingapp.databinding.ActivityBoardDeleteBinding

class BoardDelete : AppCompatActivity() {
    lateinit var binding: ActivityBoardDeleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBoardDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val DocId = intent.getStringExtra("DocId")

        if (DocId != null) {
            deleteDatabase(DocId)


            Toast.makeText(this, "게시글 삭제 완료", Toast.LENGTH_SHORT).show()

        } else {
            Log.e("BoardDelete", "Error")
            Toast.makeText(this, "게시글 삭제 실패", Toast.LENGTH_SHORT).show()
        }
    }

}