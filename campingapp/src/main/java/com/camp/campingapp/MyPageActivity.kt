package com.camp.campingapp

import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.camp.campingapp.databinding.ActivityMyPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MyPageActivity : AppCompatActivity() {
    lateinit var binding: ActivityMyPageBinding
    data class UserName(
        var username:String=""
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        if (MyApplication.checkAuth()) {
            changeVisibility("login")
        } else {
            changeVisibility("logout")
        }



//        binding.userNameView.text = MyApplication.userData?.username ?: "Guest"
        binding.emailView.text=MyApplication.email

        /*binding.profilechange.setOnClickListener {
            val intent = Intent(this@MyPageActivity, ProfileUpdateActivity::class.java)
            startActivity(intent)
        }*/

        binding.updatePasswordBtn.setOnClickListener{
            var editTextNewPassword = EditText(this)
            editTextNewPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("패스워드 변경")
            alertDialog.setMessage("변경하고 싶은 패스워드를 입력하세요")
            alertDialog.setView(editTextNewPassword)
            alertDialog.setPositiveButton("변경", {dialogInterface, i -> changePassword(editTextNewPassword.text.toString()) })
            alertDialog.setNegativeButton("취소", {dialogInterface, i -> dialogInterface.dismiss() })
            alertDialog.show()

        }


        binding.logoutBtn.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("로그아웃")
                setIcon(android.R.drawable.ic_dialog_alert)
                setMessage("로그아웃 하시겠습니까?")
                setPositiveButton("네",logoutHandler)
                setNegativeButton("아니오",logoutHandler)
                show()
            }
        }


        binding.deleteButton.setOnClickListener {
            AlertDialog.Builder(this).run{
                setTitle("탈퇴")
                setIcon(android.R.drawable.ic_delete)
                setMessage("정말 탈퇴하시겠습니까?")
                setPositiveButton("네",eventHandler)
                setNegativeButton("취소",eventHandler)
                show()
            }
//            deleteUser()

        }//oncreate

        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            try {
                val calRatio = calculateInSampleSize(
                    it.data!!.data!!,
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize)
                )
                Log.d("kkang","(caltatio):$calRatio")
                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio

                var inputStream = contentResolver.openInputStream(it.data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                inputStream = null

                bitmap?.let {
                    Log.d("kkang","결과 뷰에 적용하기 전")
                    //결과 뷰에 갤러리에서 가져온 사진을 할당 부분.
                    binding.userImageView.setImageBitmap(bitmap)
                } ?: let{
                    Log.d("kkang", "bitmap null")
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

        binding.userImageView.setOnClickListener {
            //gallery app........................
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }



    fun changeVisibility(mode: String) {
        if (mode == "loging") {
            binding.run {
                logoutBtn.visibility = View.VISIBLE
            }
        } else if (mode === "logout") {
        }
    }


}
    // ActionBar의 뒤로가기 버튼 클릭 시 호출되는 메서드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed() // 이전 화면으로 돌아가기
            return true
        }
        startActivity(Intent(this, AuthActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    fun changeVisibility(mode: String){
        if(mode === "logout") {

        } else if (mode==="login"){

        }
    }

    fun changePassword(password:String){
        FirebaseAuth.getInstance().currentUser!!.updatePassword(password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(this, "비밀번호가 변경되었습니다.", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, task.exception.toString(), Toast.LENGTH_LONG).show()

            }
        }
    }

    private  fun logoutUser(){
        //로그아웃...........
        MyApplication.auth.signOut()
        MyApplication.email = null
        //이메일 널로 할당
        Toast.makeText(
            baseContext, "로그아웃 되었습니다",
            Toast.LENGTH_SHORT
        ).show()
//            changeVisibility("logout")
        val intent = Intent(this@MyPageActivity, MainActivity::class.java)
        startActivity(intent)
    }

    private fun deleteUser(){
        val user = Firebase.auth.currentUser!!
        user.delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "탈퇴 되었습니다",Toast.LENGTH_SHORT ).show()
                }
                changeVisibility("logout")
                val intent = Intent(this@MyPageActivity, MainActivity::class.java)
                startActivity(intent)
            }
    }

    val eventHandler = object : DialogInterface.OnClickListener {
        override fun onClick(p0: DialogInterface?, p1: Int) {
            if (p1 == DialogInterface.BUTTON_POSITIVE) {
                deleteUser()
            }
        }
    }

    val logoutHandler=object :DialogInterface.OnClickListener{
        override fun onClick(dialog: DialogInterface?, lw1: Int) {
            if(lw1==DialogInterface.BUTTON_POSITIVE){
                logoutUser()
            }
        }
    }

    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try {
            var inputStream = contentResolver.openInputStream(fileUri)
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val (height: Int, width: Int) = options.run { outHeight to outWidth }

        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }


}