package com.camp.campingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.camp.campingapp.databinding.ActivityBoardUpdateBinding
import com.camp.campingapp.util.dateToString
import java.util.Date

class BoardUpdate : AppCompatActivity() {
    private lateinit var binding: ActivityBoardUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val docId = intent.getStringExtra("DocId")
        val title = intent.getStringExtra("BoardTitle")
        val content = intent.getStringExtra("BoardContent")
        val date = intent.getStringExtra("BoardDate")

        binding.BoardDate.text = date
        binding.regTitle.setText(title)
        binding.regContent.setText(content)

        // 사용자 이름 표시
        binding.userNameTextView.text = MyApplication.userData?.username ?: "Guest"

        // 수정 버튼 클릭
        binding.BoardModify.setOnClickListener {
            val data = mapOf(
                "title" to binding.regTitle.text.toString(),
                "content" to binding.regContent.text.toString(),
                "date" to dateToString(Date())
            )
            if (docId != null) {
                updateBoard(docId, data) // Use a separate function to update board
            }

            // Board 화면으로 이동
            val boardIntent = Intent(this, Board::class.java)
            startActivity(boardIntent)
            finish() // 현재 화면 종료
        }

        // 수정 취소
        binding.BoardCancel.setOnClickListener {
            finish()
        }
    }

    // Update board function
    private fun updateBoard(docId: String, data: Map<String, Any>) {
        MyApplication.db.collection("Boards").document(docId).update(data)
            .addOnSuccessListener {
                showToast("게시글 수정 완료")
            }
            .addOnFailureListener { exception ->
                Log.e("BoardUpdate", "Error updating board", exception)
                showToast("게시글 수정 실패")
            }
    }

    // Show a toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
