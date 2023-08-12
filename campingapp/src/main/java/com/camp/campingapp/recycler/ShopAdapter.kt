package com.camp.campingapp.recycler

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.camp.campingapp.ShopActivity
import com.camp.campingapp.ShopDetailActivity
import com.camp.campingapp.databinding.ItemShopBinding
import com.camp.campingapp.model.ShopList


class ShopViewHolder(val binding: ItemShopBinding): RecyclerView.ViewHolder(binding.root)
class ShopAdapter (val context: ShopActivity, val datas:List<ShopList>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
      = ShopViewHolder(ItemShopBinding.inflate(LayoutInflater.from(parent.context), parent, false))




    override fun getItemCount(): Int {
        return datas?.size ?: 0


    }
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val binding=(holder as ShopViewHolder).binding

            //add......................................
            val model = datas!![position]
            binding.name.text = model.name
            binding.addr.text = model.addr
            binding.tel.text = model.tel


            holder.binding.root.setOnClickListener {
                val intent = Intent(holder.binding.root?.context, ShopDetailActivity::class.java)
                intent.putExtra("lat", model.lat)
                intent.putExtra("lnt", model.lnt)
                ContextCompat.startActivity(holder.binding.root.context, intent, null)
            }

        }



}