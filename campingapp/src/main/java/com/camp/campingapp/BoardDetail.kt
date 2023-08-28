package com.camp.campingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.camp.campingapp.databinding.ActivityBoardDetailBinding
import com.camp.campingapp.recycler.CommentAdapter
import java.text.SimpleDateFormat
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue

class BoardDetail : AppCompatActivity() {
    private lateinit var binding: ActivityBoardDetailBinding
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var docId: String
    private lateinit var commentList: MutableMap<String, Comment>
    private lateinit var database: DatabaseReference

    data class Comment(
        var comment: String = "",
        var time: String = "",
        var username: String = ""
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        docId = intent.getStringExtra("DocId") ?: ""
        val title = intent.getStringExtra("BoardTitle")
        val content = intent.getStringExtra("BoardContent")
        val date = intent.getStringExtra("BoardDate")

        binding.BoardTitle.text = title
        binding.BoardDate.text = date
        binding.BoardContent.text = content

        binding.userNameTextView.text = MyApplication.userData?.username ?: "Guest"

        database = FirebaseDatabase.getInstance().reference

        if (docId.isNotEmpty()) {
            loadComments(docId)
        }

        binding.BoardModify.setOnClickListener {
            val intent = Intent(this, BoardUpdate::class.java)
            intent.putExtra("DocId", docId)
            intent.putExtra("BoardTitle", title)
            intent.putExtra("BoardContent", content)
            intent.putExtra("BoardDate", date)
            startActivity(intent)
            finish()
        }

        binding.BoardDelete.setOnClickListener {
            if (docId.isNotEmpty()) {
                deleteBoardAndNavigate(docId)
            }
        }

        val imgRef = MyApplication.storage.reference.child("images/${docId}.jpg")
        imgRef.downloadUrl.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Glide.with(this)
                    .load(task.result)
                    .into(binding.ImageView)
            }
        }

        binding.CommentWrite.setOnClickListener {
            val commentText = binding.BoardComment.text.toString()
            if (docId.isNotEmpty() && commentText.isNotBlank()) {
                val commentData = Comment(
                    commentText,
                    "", // Firebase 서버의 Timestamp 값을 사용하기 때문에 빈 문자열로 설정
                    MyApplication.userData?.username ?: ""
                )
                addCommentToDatabase(docId, commentData)
                binding.BoardComment.text.clear()
            }
        }
        // ActionBar에 뒤로가기 버튼 활성화
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



    }//oncreate

    // ActionBar의 뒤로가기 버튼 클릭 시 호출되는 메서드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed() // 이전 화면으로 돌아가기
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addCommentToDatabase(docId: String, comment: Comment) {
        val commentReference = database.child("Boards").child(docId).child("Comments").push()

        val commentData = Comment(
            comment.comment,
            SimpleDateFormat("yyyy-MM-dd HH:mm").format(System.currentTimeMillis()),
//            "", // Firebase 서버의 Timestamp 값을 사용하기 때문에 빈 문자열로 설정
            MyApplication.userData?.username ?: ""
        )

        commentReference.setValue(commentData)
            .addOnSuccessListener {
                loadComments(docId)
            }
            .addOnFailureListener { exception ->
                Log.e("BoardDetail", "Error adding comment", exception)
            }
    }

    private fun loadComments(docId: String) {
        database.child("Boards").child(docId).child("Comments")
            .get()
            .addOnSuccessListener { snapshot ->
                val newCommentList = mutableMapOf<String, Comment>()
                for (commentSnapshot in snapshot.children) {
                    val comment = commentSnapshot.getValue(Comment::class.java)
                    comment?.let {
                        newCommentList[commentSnapshot.key!!] = it
                    }
                }
                commentList = newCommentList

                commentAdapter = CommentAdapter(this, commentList,
                    onEditClickListener = { editPosition ->
                        val selectedComment = commentList.values.elementAt(editPosition)
                        val commentKey = commentList.keys.elementAt(editPosition)
                        showEditCommentDialog(docId, commentKey, selectedComment.comment, editPosition)
                    }
                ) { deleteCommentKey ->
                    showDeleteCommentDialog(docId, deleteCommentKey) // Changed parameter name
                }

                binding.commentRecyclerView.layoutManager = LinearLayoutManager(this)
                binding.commentRecyclerView.adapter = commentAdapter

                binding.commentRecyclerView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            }
            .addOnFailureListener { exception ->
                Log.e("BoardDetail", "Error getting comments", exception)
            }
    }

    private fun deleteBoardAndNavigate(docId: String) {
        database.child("Boards").child(docId)
            .removeValue()
            .addOnSuccessListener {
                showToastAndNavigate("게시글 삭제 완료", Board::class.java)
            }
            .addOnFailureListener { exception ->
                Log.e("BoardDetail", "Error deleting board", exception)
                showToast("게시글 삭제 실패")
            }
    }

    private fun showToastAndNavigate(message: String, destination: Class<*>) {
        showToast(message)
        val intent = Intent(this, destination)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showEditCommentDialog(docId: String, commentKey: String, comment: String, position: Int) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_edit_comment, null)
        dialogBuilder.setView(dialogView)

        val editTextComment = dialogView.findViewById<EditText>(R.id.editTextComment)

        // 기존 댓글 내용을 EditText에 설정
        editTextComment.setText(comment)

        // 다이얼로그 생성
        val alertDialog = dialogBuilder.create()

        // "확인" 버튼 클릭 리스너
        dialogView.findViewById<Button>(R.id.btnEditCommentConfirm).setOnClickListener {
            // 수정한 댓글 내용을 가져와서 처리하는 로직 추가
            val editedComment = editTextComment.text.toString()

            // 수정 로직 구현
            updateComment(docId, commentKey, editedComment)

            // 다이얼로그 닫기
            alertDialog.dismiss()
        }

        // "취소" 버튼 클릭 리스너
        dialogView.findViewById<Button>(R.id.btnEditCommentCancel).setOnClickListener {
            // 다이얼로그 닫기
            alertDialog.dismiss()
        }

        // 다이얼로그 보이기
        alertDialog.show()
    }

    private fun showDeleteCommentDialog(docId: String, commentKey: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("댓글 삭제")
        dialogBuilder.setMessage("이 댓글을 삭제하시겠습니까?")
        dialogBuilder.setPositiveButton("삭제") { _, _ ->
            // 삭제 로직 구현
            deleteComment(docId, commentKey)
        }
        dialogBuilder.setNegativeButton("취소") { _, _ ->
            // 취소 버튼 클릭 시 아무 작업 없음
        }

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }
    private fun updateComment(docId: String, commentKey: String, editedComment: String) {
        val commentData = Comment(
            editedComment,
            SimpleDateFormat("yyyy-MM-dd HH:mm").format(System.currentTimeMillis()),
            MyApplication.userData?.username ?: ""
        )
        database.child("Boards").child(docId).child("Comments").child(commentKey)
            .setValue(commentData)
            .addOnSuccessListener {
                loadComments(docId)
            }
            .addOnFailureListener { exception ->
                Log.e("BoardDetail", "Error updating comment", exception)
            }
    }

    private fun deleteComment(docId: String, commentKey: String) {
        database.child("Boards").child(docId).child("Comments").child(commentKey)
            .removeValue()
            .addOnSuccessListener {
                loadComments(docId)
            }
            .addOnFailureListener { exception ->
                Log.e("BoardDetail", "Error deleting comment", exception)
            }
    }
}
