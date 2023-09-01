package com.camp.campingapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.camp.campingapp.databinding.ActivityBoardDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class BoardDelete : AppCompatActivity() {
    private lateinit var binding: ActivityBoardDeleteBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().reference

        val docId = intent.getStringExtra("DocId")

        if (docId != null) {
            // 삭제 작업 진행
            deleteDatabaseAndNavigateToBoard(docId)
        } else {
            handleDeleteError()
        }
    }

    private fun deleteDatabaseAndNavigateToBoard(docId: String) {
        database.child("Boards").child(docId)
            .removeValue()
            .addOnSuccessListener {
                showToastAndNavigate("게시글 삭제 완료")
            }
            .addOnFailureListener { exception ->
                Log.e("BoardDelete", "Error deleting document: $docId", exception)
                handleDeleteError()
            }
    }

    private fun handleDeleteError() {
        showToast("게시글 삭제 실패")
        setResult(Activity.RESULT_CANCELED) // 삭제 실패 시 RESULT_CANCELED 설정
        finish()
    }

    private fun showToastAndNavigate(message: String) {
        showToast(message)
        setResult(Activity.RESULT_OK) // 삭제 성공 시 RESULT_OK 설정
        finish() // 액티비티 종료
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
