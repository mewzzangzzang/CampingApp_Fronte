package com.camp.campingapp


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.camp.campingapp.MyApplication.Companion.rdb
import com.camp.campingapp.databinding.ActivityAuthBinding
import com.camp.campingapp.model.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

import com.google.firebase.database.DatabaseReference

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.text.SimpleDateFormat

class AuthActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var filePath: String
    private lateinit var db: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    lateinit var binding: ActivityAuthBinding
    private lateinit var requestLauncher: ActivityResultLauncher<Intent>

//    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
         storage = FirebaseStorage.getInstance()
        auth= Firebase.auth


        //MyApplication->checkAuth=>로그인이 확인
        if(MyApplication.checkAuth()){
            changeVisibility("login")
        }else {
            changeVisibility("logout")
        }


        binding.authPhotoView.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*")

            requestLauncher.launch(intent)
        }

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

        binding.logoutBtn.setOnClickListener {
            //로그아웃...........
            MyApplication.auth.signOut()
            MyApplication.email = null
            //이메일 널로 할당
            changeVisibility("logout")
        }

        binding.goSignInBtn.setOnClickListener{
            changeVisibility("signin")
        }

        binding.signBtn.setOnClickListener {
            //이메일,비밀번호 회원가입........................
            val username = binding.authUsernameEditView.text.toString()
            val email = binding.authEmailEditView.text.toString()
            val password = binding.authPasswordEditView.text.toString()

            MyApplication.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task ->
                    saveUser()
                    binding.authEmailEditView.text.clear()
                    binding.authPasswordEditView.text.clear()
                    binding.authUsernameEditView.text.clear()
                    binding.authAddressEditView.text.clear()
                    binding.authTelEditView.text.clear()
                    if(task.isSuccessful){
                        MyApplication.auth.currentUser?.sendEmailVerification()
                            ?.addOnCompleteListener{ sendTask ->
                                if(sendTask.isSuccessful){
                                    Toast.makeText(baseContext, "회원가입에서 성공, 전송된 메일을 확인해 주세요",
                                        Toast.LENGTH_SHORT).show()
                                    changeVisibility("logout")
                                    addUserToDatabase(username, email, auth.currentUser?.uid!!)
                                }else {
                                    Toast.makeText(baseContext, "메일 발송 실패", Toast.LENGTH_SHORT).show()
                                    changeVisibility("logout")
                                }
                            }
                    }else {
                        Toast.makeText(baseContext, "회원가입 실패", Toast.LENGTH_SHORT).show()
                        changeVisibility("logout")
                }
            }
        }

        binding.loginBtn.setOnClickListener {
            //이메일, 비밀번호 로그인.......................
            val email = binding.authEmailEditView.text.toString()
            val password = binding.authPasswordEditView.text.toString()
            MyApplication.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){ task ->
                    binding.authEmailEditView.text.clear()
                    binding.authPasswordEditView.text.clear()
                    if(task.isSuccessful){
                        if(MyApplication.checkAuth()){
                            MyApplication.email = email
                            changeVisibility("login")
                            Toast.makeText(baseContext, "로그인 성공.", Toast.LENGTH_SHORT).show()
                            finish()
                            val intent = Intent(this@AuthActivity, MainActivity::class.java)
                            startActivity(intent)
                            System.runFinalization()
                        }else {
                            Toast.makeText(baseContext, "전송된 메일로 이메일 인증이 되지 않았습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }else {
                        Toast.makeText(baseContext, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        // ActionBar에 뒤로가기 버튼 활성화
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }//oncreate닫음


        // ActionBar의 뒤로가기 버튼 클릭 시 호출되는 메서드
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (item.itemId == android.R.id.home) {
                onBackPressed() // 이전 화면으로 돌아가기
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
            .into(binding.authPhotoView)
    }
    private fun uploadImage(docId: String, imageUrl: String){
        db.collection("user").document(docId)
            .update("imageUrl", imageUrl)
            .addOnSuccessListener {
                Toast.makeText(this, "save ok..", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener{
                Toast.makeText(this, "save fail..", Toast.LENGTH_SHORT).show()
            }

    }
    private fun ImageInFirestore(docId: String) {
        val storageRef = storage.reference
        val imgRef = storageRef.child("images/${docId}.jpg")
        val file = Uri.fromFile(File(filePath))
        imgRef.putFile(file)
            .addOnSuccessListener {
                imgRef.downloadUrl.addOnSuccessListener { uri ->
                    uploadImage(docId, uri.toString())
                }
            }
            .addOnFailureListener {
                Log.d("khs", "data save error", it)
            }
    }

    private fun saveUser() {
        val uid = MyApplication.auth.currentUser?.uid ?: ""
        val data = mapOf(
            "uid" to uid,
            "email" to binding.authEmailEditView.text.toString(),
            "password" to binding.authPasswordEditView.text.toString(),
            "username" to binding.authUsernameEditView.text.toString(),
            "address" to binding.authAddressEditView.text.toString(),
            "tel" to binding.authTelEditView.text.toString(),
        )
        db.collection("user")
            .document(uid).set(data)
            .addOnSuccessListener { uri->
                ImageInFirestore(uid)
            }
            .addOnFailureListener {
                Log.d("khs", "data save error", it)
            }
    }

    private fun addUserToDatabase(name: String, email: String, uId: String){
        rdb.child("user").child(uId).setValue(User(name, email, uId))

    }

    //매개변수를 모드라는 변수명,문자열 타입.
    fun changeVisibility(mode: String){
        if(mode === "login"){
            binding.run {
                authMainTextView.text = "${MyApplication.email} 님 반갑습니다."
                authNotEmail.visibility=View.GONE
                logoutBtn.visibility= View.VISIBLE
                goSignInBtn.visibility= View.GONE
                authUsernameEditView.visibility=View.GONE
                authEmailEditView.visibility= View.GONE
                authPasswordEditView.visibility= View.GONE
                authAddressEditView.visibility=View.GONE
                authUsernameEditView.visibility=View.GONE
                loginBtn.visibility= View.GONE
                authTelEditView.visibility=View.GONE
                signBtn.visibility= View.GONE
                authPhotoView.visibility=View.GONE

            }

        }else if(mode === "logout"){
            binding.run {
                authMainTextView.text = "로그인 하거나 회원가입 해주세요."
                logoutBtn.visibility = View.GONE
                goSignInBtn.visibility = View.VISIBLE
                authNotEmail.visibility=View.VISIBLE
                authUsernameEditView.visibility = View.GONE
                authEmailEditView.visibility = View.VISIBLE
                authPasswordEditView.visibility = View.VISIBLE
                authAddressEditView.visibility=View.GONE
                authUsernameEditView.visibility=View.GONE
                signBtn.visibility = View.GONE
                authTelEditView.visibility=View.GONE
                loginBtn.visibility = View.VISIBLE
                authPhotoView.visibility=View.GONE

            }
        }else if(mode === "signin"){
            binding.run {
                logoutBtn.visibility = View.GONE
                goSignInBtn.visibility = View.GONE
                loginBtn.visibility = View.GONE
                authNotEmail.visibility=View.GONE
                authUsernameEditView.visibility = View.VISIBLE
                signBtn.visibility = View.VISIBLE
                authTelEditView.visibility=View.VISIBLE
                authEmailEditView.visibility = View.VISIBLE
                authPasswordEditView.visibility = View.VISIBLE
                authAddressEditView.visibility=View.VISIBLE
                authUsernameEditView.visibility=View.VISIBLE
                authPhotoView.visibility=View.VISIBLE

            }
        }
        else if(mode==="h_signin"){
            binding.run{
                loginBtn.visibility = View.GONE
                authNotEmail.visibility=View.GONE
                logoutBtn.visibility = View.GONE
                goSignInBtn.visibility = View.GONE
                signBtn.visibility = View.GONE
                authTelEditView.visibility=View.GONE
                authEmailEditView.visibility = View.VISIBLE
                authPasswordEditView.visibility = View.VISIBLE
                authAddressEditView.visibility=View.GONE
                authUsernameEditView.visibility=View.GONE

            }
        }
        else if(mode==="G_signin"){
            binding.run{
                loginBtn.visibility = View.GONE
                authNotEmail.visibility=View.GONE
                logoutBtn.visibility = View.GONE
                goSignInBtn.visibility = View.GONE
                authUsernameEditView.visibility = View.VISIBLE
                signBtn.visibility = View.GONE
                authEmailEditView.visibility = View.VISIBLE
                authPasswordEditView.visibility = View.VISIBLE
                authAddressEditView.visibility=View.VISIBLE
                authUsernameEditView.visibility=View.VISIBLE
                authTelEditView.visibility=View.VISIBLE
            }
        }
    }
}