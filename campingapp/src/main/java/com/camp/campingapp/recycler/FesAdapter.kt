package com.camp.campingapp.recycler

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.camp.campingapp.FesActivity
import com.camp.campingapp.FesDetailActivity
import com.camp.campingapp.R
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
        val animation = AnimationUtils.loadAnimation(holder.binding.root.context, R.anim.list_item_ani)
        holder.binding.root.animation = animation

        //add......................................
        val model = datas!![position]
        binding.fesname.text = model.fesname
        binding.startdate.text = model.startdate
        binding.enddate.text = model.enddate
        binding.addr.text = model.addr
        binding.tel.text = model.tel
        val urlImg = model.firstImageUrl
        Glide.with(context)
            .asBitmap()
            .load(urlImg)
            .into(object : CustomTarget<Bitmap>(200, 200) {

                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    binding.avatarView.setImageBitmap(resource)
//                    Log.d("lsy", "width : ${resource.width}, height: ${resource.height}")
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }
            })


        //클릭시 축제 상세정보 페이지에 정보넘기기
        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.binding.root?.context, FesDetailActivity::class.java)
            intent.putExtra("lat", model.lat)
            intent.putExtra("lnt", model.lnt)
            intent.putExtra("fesname", model.fesname)
            intent.putExtra("startdate", model.startdate)
            intent.putExtra("enddate", model.enddate)
            intent.putExtra("content", model.content)
            intent.putExtra("tel", model.tel)
            intent.putExtra("pageaddr", model.pageaddr)
            intent.putExtra("firstImageUrl", model.firstImageUrl)




            ContextCompat.startActivity(holder.binding.root.context, intent, null)
        }

    }



}