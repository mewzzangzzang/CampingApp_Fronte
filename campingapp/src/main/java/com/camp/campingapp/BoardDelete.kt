package com.camp.campingapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.camp.campingapp.databinding.ActivityBoardDeleteBinding

class BoardDelete : AppCompatActivity() {
    private lateinit var binding: ActivityBoardDeleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val docId = intent.getStringExtra("DocId")

        if (docId != null) {
            deleteDatabaseAndNavigateToBoard(docId)
        } else {
            handleDeleteError()
        }
    }

    private fun deleteDatabaseAndNavigateToBoard(docId: String) {
        deleteDatabase(docId)
        showToastAndNavigate("게시글 삭제 완료", Board::class.java)
    }

    private fun handleDeleteError() {
        showToast("게시글 삭제 실패")
        // 추가적인 오류 처리 로직을 여기에 추가할 수 있음
    }

    private fun showToastAndNavigate(message: String, destination: Class<*>) {
        showToast(message)
        startActivity(Intent(this, destination).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
        finish()
    }

    private fun showToast(message: String) {
        showToast(message)
    }
}
