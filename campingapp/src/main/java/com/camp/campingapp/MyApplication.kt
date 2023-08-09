package com.camp.campingapp


import androidx.multidex.MultiDexApplication
import com.camp.campingapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import retrofit2.converter.gson.GsonConverterFactory
import com.camp.campingapp.retrofit.NetworkServiceDoNm
import retrofit2.Retrofit


class MyApplication: MultiDexApplication() {
    companion object {
        lateinit var auth: FirebaseAuth
        var email: String? = null
        lateinit var db: FirebaseFirestore
        lateinit var storage: FirebaseStorage
        lateinit var rdb: DatabaseReference


        fun checkAuth(): Boolean {
            var currentUser = auth.currentUser
            return currentUser?.let {
                email = currentUser.email
                currentUser.isEmailVerified
            } ?: let {
                false
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()
        storage = Firebase.storage
        rdb = Firebase.database.reference

    //add....................................
    var networkService: NetworkServiceDoNm

    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("http://10.100.103.43:8083/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    init {
        networkService = retrofit.create(NetworkServiceDoNm::class.java)

    }
}