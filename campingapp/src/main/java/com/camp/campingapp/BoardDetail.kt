package com.camp.campingapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.camp.campingapp.databinding.ActivityBoardDetailBinding
import com.camp.campingapp.recycler.CommentAdapter
import java.text.SimpleDateFormat
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BoardDetail : AppCompatActivity() {
    private lateinit var binding: ActivityBoardDetailBinding
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var docId: String
    private lateinit var commentList: MutableMap<String, Comment>
    private lateinit var database: DatabaseReference
    private lateinit var firestore: FirebaseFirestore // Firestore 레퍼런스 추가

    data class Comment(
        var comment: String = "",
        var time: String = "",
        var username: String = ""
    )

    companion object {
        const val DELETE_REQUEST_CODE = 123 // Define a request code
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        docId = intent.getStringExtra("DocId") ?: ""
        val title = intent.getStringExtra("BoardTitle")
        val content = intent.getStringExtra("BoardContent")
        val date = intent.getStringExtra("BoardDate")

        firestore = FirebaseFirestore.getInstance() // Firestore 레퍼런스 초기화

        binding.BoardTitle.text = title
        binding.BoardDate.text = date
        binding.BoardContent.text = content

        binding.userNameTextView.text = MyApplication.userData?.username ?: "Guest"

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
        val docRef = firestore.collection("Boards").document(docId) // Firestore 문서 레퍼런스 가져오기

        docRef.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                docRef.delete()
                    .addOnSuccessListener {
                        Log.d("BoardDetail", "Success deleting document: $docId")
                        showToast("게시글이 삭제되었습니다.")
                        // 원하는 동작 수행 (예: 업데이트 UI 또는 리스트 갱신)

                        // Board 액티비티로 돌아가기
                        val intent = Intent(this, Board::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener { exception ->
                        Log.e("BoardDetail", "Error deleting document: $docId", exception)
                        showToast("게시글 삭제 실패")
                        // 원하는 동작 수행
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
            SimpleDateFormat("yyyy-MM-dd HH:mm").format(System.currentTimeMillis()),
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
