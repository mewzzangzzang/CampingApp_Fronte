package com.camp.campingapp.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.camp.campingapp.databinding.ItemMainBinding
import com.camp.campingapp.model.HostData
import com.camp.campingapp.test.HostApplication


class HostViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)
class HostAdapter(val context:Context,val HostList: MutableList<HostData>):RecyclerView.Adapter<HostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HostViewHolder(ItemMainBinding.inflate(layoutInflater))
    }

    override fun getItemCount(): Int {
        return HostList.size
    }

    override fun onBindViewHolder(holder: HostViewHolder, position: Int) {
        val Hdata = HostList.get(position)

        holder.binding.run {
            itemEmailView.text = Hdata.email
            itemDateView.text = Hdata.date
            itemContentView.text = Hdata.content
            itemTypeView.text = Hdata.type
//            itemTelView.text=Hdata.tel
//            itemAddrView.text=Hdata.addr
//            itemHnameView.text=Hdata.Hname

        }

        val imgRef = HostApplication.storage.reference.child("images/${Hdata.HId}.jpg")
        imgRef.downloadUrl.addOnCompleteListener{ task ->

            if(task.isSuccessful){
                Glide.with(context)
                    .load(task.result)
                    .into(holder.binding.itemImageView)
            }
        }
    }
}
