package com.camp.campingapp.recycler

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.camp.campingapp.ChatMainActivity
import com.camp.campingapp.MyApplication
import com.camp.campingapp.databinding.ItemCampBinding
import com.camp.campingapp.model.HostData

class HostListAdapter(private val context: Context, private val hostList: List<HostData>) :
    RecyclerView.Adapter<HostListAdapter.HostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HostViewHolder {
        val binding = ItemCampBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HostViewHolder(binding)
    }

    override fun getItemCount(): Int = hostList.size

    override fun onBindViewHolder(holder: HostViewHolder, position: Int) {
        holder.bind(hostList[position])
    }

    inner class HostViewHolder(private val binding: ItemCampBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hostData: HostData) {
            binding.apply {
                // 호스트 데이터를 뷰에 설정
                campName.text = hostData.campname
                hostName.text = hostData.hostname
                campAddr.text = hostData.addr
                campTel.text = hostData.tel
                campIntro.text = hostData.content
                // 나머지 데이터 설정

                // 콜 버튼 클릭 이벤트 설정
                callButton.setOnClickListener {
                    val phoneNumber = hostData.tel
                    if (!phoneNumber.isNullOrEmpty()) {
                        val dialIntent = Intent(Intent.ACTION_DIAL)
                        dialIntent.data = Uri.parse("tel:$phoneNumber")
                        context.startActivity(dialIntent)
                    }
                }

                // 채팅 버튼 클릭 이벤트 설정
                chatButton.setOnClickListener {
                    val chatIntent = Intent(context, ChatMainActivity::class.java)
                    chatIntent.putExtra("hostData", hostData)
                    context.startActivity(chatIntent)
                }

                val imgRef = MyApplication.storage.reference.child("images/${hostData.hid}.jpg")
                imgRef.downloadUrl.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Glide.with(itemView.context)
                            .load(task.result)
                            .apply(RequestOptions().override(250, 200))
                            .centerCrop()
                            .into(campPhoto)


                    }
                }
            }
        }
    }
}