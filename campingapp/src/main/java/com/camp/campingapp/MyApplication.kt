package com.camp.campingapp


import android.util.Log
import androidx.multidex.MultiDexApplication
import com.camp.campingapp.model.UserData

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import retrofit2.converter.gson.GsonConverterFactory
import com.camp.campingapp.retrofit.NetworkServiceDoNm
import com.google.firebase.storage.ktx.storage
import retrofit2.Retrofit


class MyApplication : MultiDexApplication() {
    companion object {
        lateinit var auth: FirebaseAuth
        var email: String? = null
        lateinit var db: FirebaseFirestore
        lateinit var storage: FirebaseStorage
        lateinit var rdb: DatabaseReference
        lateinit var networkService: NetworkServiceDoNm

        // userData 초기화
        var userData: UserData? = null

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
        Log.d("MyApplication", "onCreate method is called")
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()
        storage = Firebase.storage
        rdb = Firebase.database.reference

        if (auth.currentUser != null) {
            fetchUserData() // 사용자 데이터 초기화
        } else {
            Log.d("MyApplication", "currentUser is null")
        }
    }

    private fun fetchUserData() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            db.collection("user")
                .document(currentUser.uid)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val document = task.result
                        if (document != null && document.exists()) {
                            val fetchedUserData = document.toObject(UserData::class.java)
                            userData = fetchedUserData // userData 초기화
                            Log.d("MyApplication", "Fetched userData: $userData")
                        } else {
                            Log.d("MyApplication", "No such document")
                        }
                    } else {
                        Log.e("MyApplication", "Error getting userData", task.exception)
                    }
                }
        } else {
            Log.d("MyApplication", "currentUser is null")
        }
    }

    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("http://10.100.103.76:8083/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    var networkService: NetworkServiceDoNm = retrofit.create(NetworkServiceDoNm::class.java)

}

val naver: Retrofit
    get() = Retrofit.Builder()
        .baseUrl("https://naveropenapi.apigw.ntruss.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

var networkService: NetworkServiceDoNm = naver.create(NetworkServiceDoNm::class.java)