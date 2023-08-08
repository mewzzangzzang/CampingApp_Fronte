package com.camp.campingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ChatActivity : AppCompatActivity() {

    private lateinit var receiverName: String
    private lateinit var receuverUid: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        //넘어온 데이터 변수에 담기
        receiverName=intent.getStringExtra("name").toString()
        receuverUid=intent.getStringExtra("uId").toString()

        //액션바에 상대방 이름 보여주기
        supportActionBar?.title = receiverName
    }
}