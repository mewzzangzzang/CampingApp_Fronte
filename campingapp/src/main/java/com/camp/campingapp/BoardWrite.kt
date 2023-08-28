package com.camp.campingapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.camp.campingapp.databinding.ActivityWriteBinding
import com.camp.campingapp.util.dateToString
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.util.Date

class BoardWrite : AppCompatActivity() {
    private lateinit var binding: ActivityWriteBinding
    private lateinit var filePath: String
    private lateinit var db: FirebaseFirestore
    private lateinit var storage: FirebaseStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()

        // MyApplication.username을 가져와서 username TextView에 설정
        val loggedInUsername = MyApplication.userData?.username
        if (loggedInUsername != null) {
            binding.username.text = loggedInUsername
        } else {
            binding.username.text = "비회원"
        }

        binding.postbtn.setOnClickListener {
            // Save the data including username
            saveBoardData()
        }

        binding.upload.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
            requestLauncher.launch(intent)
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

    private val requestLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == android.app.Activity.RESULT_OK) {
            val selectedImageUri = result.data?.data
            selectedImageUri?.let {
                filePath = getRealPathFromUri(it)
                loadImageToImageView(it)
            }
        }
    }

    private fun getRealPathFromUri(uri: Uri): String {
        val cursor = contentResolver.query(
            uri, arrayOf(MediaStore.Images.Media.DATA), null, null, null
        )
        cursor?.use {
            it.moveToFirst()
            return it.getString(it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
        }
        return ""
    }

    private fun loadImageToImageView(imageUri: Uri) {
        Glide.with(applicationContext)
            .load(imageUri)
            .apply(RequestOptions().override(250, 200))
            .centerCrop()
            .into(binding.imageView)
    }

    private fun saveBoardData() {
        val username = MyApplication.userData?.username ?: ""

        val boardData = mapOf(
            "title" to binding.title.text.toString(),
            "content" to binding.addEditView.text.toString(),
            "date" to dateToString(Date()),
            "username" to username
        )

        db.collection("Boards")
            .add(boardData)
            .addOnSuccessListener { documentReference ->
                val docId = documentReference.id
                uploadImageToStorage(docId)
            }
            .addOnFailureListener {
                showToast("Error saving board data")
            }
    }

    private fun uploadImageToStorage(docId: String) {
        val storageRef = storage.reference
        val imgRef = storageRef.child("images/${docId}.jpg")
        val file = Uri.fromFile(File(filePath))

        imgRef.putFile(file)
            .addOnSuccessListener {
                imgRef.downloadUrl.addOnSuccessListener { uri ->
                    updateImageUrlInFirestore(docId, uri.toString())
                }
            }
            .addOnFailureListener {
                showToast("Image upload failed")
            }
    }

    private fun updateImageUrlInFirestore(docId: String, imageUrl: String) {
        db.collection("Boards").document(docId)
            .update("imageUrl", imageUrl)
            .addOnSuccessListener {
                showToastAndFinish("Upload successful")
            }
            .addOnFailureListener {
                showToast("Image URL update failed")
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showToastAndFinish(message: String) {
        showToast(message)
        finish()
    }
}
