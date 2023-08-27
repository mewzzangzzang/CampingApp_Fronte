package com.camp.campingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.camp.campingapp.databinding.ActivityChatBinding
import com.camp.campingapp.model.Message
import com.camp.campingapp.recycler.MessageAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ChatActivity : AppCompatActivity() {

    private lateinit var receiverName: String
    private lateinit var receiverUid: String
    private lateinit var binding: ActivityChatBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var rdb: DatabaseReference
    private lateinit var receiverRoom: String
    private lateinit var senderRoom: String
    private lateinit var messageList: ArrayList<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        messageList = ArrayList()
        val messageAdapter = MessageAdapter(this, messageList)
        binding.chatRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.chatRecyclerView.adapter = messageAdapter

        receiverName = intent.getStringExtra("name").toString()
        receiverUid = intent.getStringExtra("uId").toString()
        auth = FirebaseAuth.getInstance()
        rdb = FirebaseDatabase.getInstance().reference
        val senderUid = auth.currentUser?.uid
        senderRoom = receiverUid + senderUid
        receiverRoom = senderUid + receiverUid
        supportActionBar?.title = receiverName

        binding.sendBtn.setOnClickListener {
            val message = binding.messageEdit.text.toString()
            val messageObject = Message(message, senderUid)
            val messageRef = rdb.child("chats")

            messageRef.child(senderRoom).child("messages").push().setValue(messageObject)
                .addOnSuccessListener {
                    messageRef.child(receiverRoom).child("messages").push().setValue(messageObject)
                }

            binding.messageEdit.setText("")
        }

        rdb.child("chats").child(senderRoom).child("messages")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()
                    for (postSnapshot in snapshot.children) {
                        val message = postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    // 실패 시 실행
                }
            })
    }
}