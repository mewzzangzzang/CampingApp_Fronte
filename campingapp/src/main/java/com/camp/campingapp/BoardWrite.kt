package com.camp.campingapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.camp.campingapp.MyApplication.Companion.db
import com.camp.campingapp.MyApplication.Companion.storage
import com.camp.campingapp.databinding.ActivityWriteBinding
import com.camp.campingapp.util.dateToString
import java.io.File
import java.util.Date


class BoardWrite : AppCompatActivity() {
//    lateinit var db: FirebaseFirestore
//    lateinit var storage: FirebaseStorage

    lateinit var binding: ActivityWriteBinding
    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        db = FirebaseFirestore.getInstance()
//        storage = Firebase.storage

        binding.postbtn.setOnClickListener {
            saveStore()
        }

        binding.upload.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK)
                intent.setDataAndType(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    "image/*"
                )
                requestLauncher.launch(intent)

        }

    }

    private val requestLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode === android.app.Activity.RESULT_OK) {
            Glide
                .with(applicationContext)
                .load(it.data?.data)
                .apply(RequestOptions().override(250, 200))
                .centerCrop()
                .into(binding.imageView)
            val cursor = contentResolver.query(
                it.data?.data as Uri,
                arrayOf<String>(MediaStore.Images.Media.DATA), null, null, null
            )
            cursor?.moveToFirst().let {
                filePath = cursor?.getString(0) as String
            }
        }
    }

    private fun saveStore() {
        val data = mapOf(
            "title" to binding.title.text.toString(),
            "content" to binding.addEditView.text.toString(),
            "date" to dateToString(Date())
        )
        db.collection("Boards")
            .add(data)
            .addOnSuccessListener {
                uploadImage(it.id)
            }
            .addOnFailureListener {
                Toast.makeText(this, "error!!", Toast.LENGTH_SHORT).show()
            }
        finish()
    }

    private fun uploadImage(docId: String) {
        val storage = storage
        val storageRef = storage.reference
        val imgRef = storageRef.child("images/${docId}.jpg")

        val file = Uri.fromFile(File(filePath))
        imgRef.putFile(file)
            .addOnSuccessListener {
                Toast.makeText(this, "upload ok", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "upload fail", Toast.LENGTH_SHORT).show()

            }
    }

}


