package com.camp.campingapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.camp.campingapp.databinding.ActivityBoardDetailBinding
import com.camp.campingapp.recycler.CommentAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date

class BoardDetail : AppCompatActivity() {
    private lateinit var binding: ActivityBoardDetailBinding
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var docId: String
    private lateinit var commentList: MutableMap<String, Comment>
    private lateinit var database: DatabaseReference
    private lateinit var firestore: FirebaseFirestore

    data class Comment(
        var comment: String = "",
        var time: String = "",
        var username: String = ""
    )

    companion object {
        const val DELETE_REQUEST_CODE = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 게시글 데이터 및 작성자 정보 가져오기
        docId = intent.getStringExtra("DocId") ?: ""
        val title = intent.getStringExtra("BoardTitle") ?: ""
        val content = intent.getStringExtra("BoardContent") ?: ""
        val date = intent.getStringExtra("BoardDate") ?: ""
        val authorUid = intent.getStringExtra("AuthorUid") ?: ""
        val username = intent.getStringExtra("Username") ?: "" // 이 부분 추가

        firestore = FirebaseFirestore.getInstance()
        binding.BoardTitle.text = title
        binding.BoardDate.text = date
        binding.BoardContent.text = content

        // 현재 사용자 정보 가져오기
        val currentUser = FirebaseAuth.getInstance().currentUser
        val currentUserUid = currentUser?.uid

        // 작성자 정보와 현재 사용자 UID 비교하여 수정 및 삭제 버튼 표시 여부 결정
        if (currentUserUid == authorUid) {
            binding.BoardModify.visibility = View.VISIBLE
            binding.BoardDelete.visibility = View.VISIBLE
        } else {
            binding.BoardModify.visibility = View.GONE
            binding.BoardDelete.visibility = View.GONE
        }

        database = FirebaseDatabase.getInstance().reference

        if (docId.isNotEmpty()) {
            loadComments(docId)
        }

        binding.BoardModify.setOnClickListener {
            val intent = Intent(this, BoardUpdate::class.java).apply {
                putExtra("DocId", docId)
                putExtra("BoardTitle", title)
                putExtra("BoardContent", content)
                putExtra("BoardDate", date)
            }
            startActivity(intent)
            finish()
        }

        binding.BoardDelete.setOnClickListener {
            if (docId.isNotEmpty()) {
                deleteBoard(docId)
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
                    "",
                    MyApplication.userData?.username ?: ""
                )
                addCommentToDatabase(docId, commentData)
                binding.BoardComment.text.clear()
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // 뒤로 가기 버튼 클릭 시 액티비티 종료
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun deleteBoard(docId: String) {
        val docRef = firestore.collection("Boards").document(docId)

        docRef.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                docRef.delete()
                    .addOnSuccessListener {
                        Log.d("BoardDetail", "Success deleting document: $docId")
                        showToast("게시글이 삭제되었습니다.")
                        val intent = Intent(this, Board::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener { exception ->
                        Log.e("BoardDetail", "Error deleting document: $docId", exception)
                        showToast("게시글 삭제 실패")
                    }
            } else {
                showToast("해당 게시글이 이미 삭제되었습니다.")
            }
        }.addOnFailureListener { exception ->
            Log.e("BoardDetail", "Error checking document existence: $docId", exception)
        }
    }

    private fun addCommentToDatabase(docId: String, comment: Comment) {
        val commentReference = database.child("Boards").child(docId).child("Comments").push()

        val commentData = Comment(
            comment.comment,
            SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date()),
            comment.username
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
                        if (selectedComment.username == MyApplication.userData?.username) {
                            showEditCommentDialog(docId, commentKey, selectedComment.comment, editPosition)
                        } else {
                            showToast("자신이 작성한 댓글만 수정할 수 있습니다.")
                        }
                    }
                ) { deleteCommentKey ->
                    showDeleteCommentDialog(docId, deleteCommentKey)
                }

                binding.commentRecyclerView.layoutManager = LinearLayoutManager(this)
                binding.commentRecyclerView.adapter = commentAdapter

                binding.commentRecyclerView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            }
            .addOnFailureListener { exception ->
                Log.e("BoardDetail", "Error getting comments", exception)
            }
    }

    private fun showEditCommentDialog(docId: String, commentKey: String, comment: String, position: Int) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_edit_comment, null)
        dialogBuilder.setView(dialogView)

        val editTextComment = dialogView.findViewById<EditText>(R.id.editTextComment)
        editTextComment.setText(comment)

        val alertDialog = dialogBuilder.create()

        dialogView.findViewById<Button>(R.id.btnEditCommentConfirm).setOnClickListener {
            val editedComment = editTextComment.text.toString()
            updateComment(docId, commentKey, editedComment)
            alertDialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.btnEditCommentCancel).setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    private fun showDeleteCommentDialog(docId: String, commentKey: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("댓글 삭제")
        dialogBuilder.setMessage("이 댓글을 삭제하시겠습니까?")
        dialogBuilder.setPositiveButton("삭제") { _, _ ->
            deleteComment(docId, commentKey)
        }
        dialogBuilder.setNegativeButton("취소") { _, _ -> }

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    private fun updateComment(docId: String, commentKey: String, editedComment: String) {
        val commentData = Comment(
            editedComment,
            SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date()),
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
