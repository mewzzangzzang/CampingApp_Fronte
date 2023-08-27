package com.camp.campingapp




import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.camp.campingapp.databinding.ActivityHostListItemBinding
import com.camp.campingapp.util.dateToString
import com.facebook.FacebookSdk.getApplicationContext
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.io.File
import java.util.*

class HostListItemActivity : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance()

    lateinit var binding: ActivityHostListItemBinding
    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHostListItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    val requestLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    )
    {
        if (it.resultCode === android.app.Activity.RESULT_OK) {
            Glide
                .with(getApplicationContext())
                .load(it.data?.data)
                .apply(RequestOptions().override(250, 200))
                .centerCrop()
                .into(binding.addImageView)


            val cursor = contentResolver.query(
                it.data?.data as Uri,
                arrayOf<String>(MediaStore.Images.Media.DATA), null, null, null
            );
            cursor?.moveToFirst().let {
                filePath = cursor?.getString(0) as String
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === R.id.menu_add_gallery) {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            requestLauncher.launch(intent)
        } else if (item.itemId === R.id.menu_add_save) {
            saveStore()
        }


        return super.onOptionsItemSelected(item)
    }
    //....................
    private fun saveStore(){
        //add............................
        val data = mapOf(
            "campname" to binding.addCampNameView.text.toString(),
            "hostname" to binding.hostNameView.text.toString(),
            "tel" to binding.hostTelView.text.toString(),
            "addr" to binding.hostAddr.text.toString(),
            "content" to binding.addEditView.text.toString(),
            "date" to dateToString(Date())
        )

        db.collection("Camping")
            .add(data)
            .addOnSuccessListener {
                 Log.d("test", data.toString())
                uploadImage(it.id)
            }
            .addOnFailureListener{
                Log.d("kkang", "data save error", it)
            }

    }
    private fun uploadImage(hid: String){
        //add............................
        val storage = MyApplication.storage
        val storageRef = storage.reference
        val imgRef = storageRef.child("images/${hid}.jpg")

        val file = Uri.fromFile(File(filePath))
        imgRef.putFile(file)
            .addOnSuccessListener {
                Toast.makeText(this, "save ok..", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener{
                Log.d("kkang", "file save error", it)
            }

    }
}