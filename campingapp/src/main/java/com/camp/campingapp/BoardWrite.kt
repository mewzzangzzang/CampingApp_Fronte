package com.camp.campingapp

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.camp.campingapp.MyApplication
import com.camp.campingapp.databinding.ActivityWriteBinding
import com.camp.campingapp.util.dateToString
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.util.Date

class BoardWrite : AppCompatActivity() {
    private lateinit var binding: ActivityWriteBinding
    private lateinit var filePath: String
    private lateinit var db: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    private lateinit var requestLauncher: ActivityResultLauncher<Intent>
    val customColor = Color.parseColor("#6A856B")


    companion object {
        const val REQUEST_CODE_ADD_BOARD = 123
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()
        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid
        Log.d("BoardWrite", "현재UID: $currentUserUid")



        val loggedInUsername = MyApplication.userData?.username
        binding.username.text = loggedInUsername ?: "Guest"
//        val loggedInUsername = MyApplication.userData?.username
//           binding.username.text = loggedInUsername
        Log.d("zzz", "현재UID: $loggedInUsername")



//        binding.postbtn.setOnClickListener {
//            saveBoardData(currentUserUid)
//        }
        binding.postbtn.setOnClickListener{
            saveBoardData(currentUserUid)
        }
        binding.postbtn.setOnClickListener {
            if (binding.postbtn.isEnabled) {
                if (binding.title.text.isEmpty()) {
                    showToast("텍스트를 입력하세요.")
                } else {
//                    saveBoardData(currentUserUid)
                }
            }
        }

        binding.upload.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
            requestLauncher.launch(intent)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedImageUri = result.data?.data
                selectedImageUri?.let {
                    filePath = getRealPathFromUri(it)
                    loadImageToImageView(it)
                }
            }
        }

//     binding.title editText입력시 버튼 활성화 기능
        binding.title.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                //텍스트를 입력 후
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //텍스트 입력 전
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //텍스트 입력 중
                if(binding.title.length() < 1) { // 제목이 1글자이상
                    binding.postbtn.isClickable = false // 버튼 클릭할수 없게
                    binding.postbtn.isEnabled = false // 버튼 비활성화
                    showToast("제목을 입력해주세요")

                } else {
                    binding.postbtn.isClickable = true // 버튼 클릭할수 있게
                    binding.postbtn.isEnabled = true // 버튼 활성화
                    binding.postbtn.setBackgroundColor(customColor)

                }
            }


        })
        
    }//oncreate닫음



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
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

    private fun saveBoardData(currentUserUid: String?) {
        val username = MyApplication.userData?.username ?: ""

        val boardData = mapOf(
            "title" to binding.title.text.toString(),
            "content" to binding.addEditView.text.toString(),
            "date" to dateToString(Date()),
//            "username" to username,
            "username" to username,
            "uid" to currentUserUid // 현재 사용자의 UID 추가
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
        setResult(Activity.RESULT_OK)
        finish()
    }



}
