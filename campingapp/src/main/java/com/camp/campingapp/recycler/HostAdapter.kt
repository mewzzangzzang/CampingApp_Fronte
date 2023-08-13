package com.camp.campingapp.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.camp.campingapp.MyApplication
import com.camp.campingapp.databinding.HostListItemBinding
import com.camp.campingapp.databinding.ItemMainBinding
import com.camp.campingapp.model.HostData


class HostAdapter(
    private val context: Context,
    private val itemList: MutableList<HostData> = mutableListOf(),
    private val clickListener: (HostData) -> Unit
) :
    RecyclerView.Adapter<HostAdapter.HostViewHolder>() {


    inner class HostViewHolder(val binding: HostListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HostListItemBinding.inflate(inflater, parent, false)
        return HostViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: HostViewHolder, position: Int) {
        val data = itemList[position]

        holder.binding.run {
            HostCampNameView.text = data.campname



            val imgRef = MyApplication.storage.reference.child("images/${data.hid}.jpg")
            imgRef.downloadUrl.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Glide.with(context)
                        .load(task.result)
                        .into(HostCampImageView)
                }
            }

            root.setOnClickListener {
                // 클릭리스너로 호스트 데이터 불러오기
                clickListener(data)


            }
        }
    }
}