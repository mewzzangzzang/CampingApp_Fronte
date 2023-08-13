package com.camp.campingapp.recycler

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.camp.campingapp.FesActivity
import com.camp.campingapp.FesDetailActivity
import com.camp.campingapp.databinding.ItemFesBinding
import com.camp.campingapp.model.FesList

class FesViewHolder (val binding: ItemFesBinding): RecyclerView.ViewHolder(binding.root)

class FesAdapter(val context: FesActivity, val datas:List<FesList>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = FesViewHolder(ItemFesBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int {
        return datas?.size?:0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as FesViewHolder).binding

        //add......................................
        val model = datas!![position]
        binding.fesname.text = model.fesname
        binding.startdate.text = model.startdate
        binding.enddate.text = model.enddate
        binding.addr.text = model.addr
        binding.tel.text = model.tel


        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.binding.root?.context, FesDetailActivity::class.java)
            intent.putExtra("lat", model.lat)
            intent.putExtra("lnt", model.lnt)
            ContextCompat.startActivity(holder.binding.root.context, intent, null)
        }

    }



}