package com.camp.campingapp.recycler

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.camp.campingapp.CampGlampingActivity
import com.camp.campingapp.DoNmDetailActivity
import com.camp.campingapp.databinding.ItemDonmBinding
import com.camp.campingapp.model.GlampList


class GlampingViewHolder(val binding: ItemDonmBinding) : RecyclerView.ViewHolder(binding.root)

class GlampingListAdapter(val context: CampGlampingActivity, val datas: List<GlampList>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        GlampingViewHolder(ItemDonmBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    // 리사이클러뷰
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as GlampingViewHolder).binding

        //add......................................
        val model = datas!![position]
        binding.facltNm.text = model.facltNm
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
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }
            })

        // 클릭시 캠핑장 상세 정보 페이지
        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.binding.root?.context, DoNmDetailActivity::class.java)
            intent.putExtra("facltNm", model.facltNm)
            intent.putExtra("urlImg", model.firstImageUrl)
            intent.putExtra("tel", model.tel)
            intent.putExtra("lineIntro", model.lineIntro)
            intent.putExtra("induty", model.induty)
            intent.putExtra("sbrsCl", model.sbrsCl)
            intent.putExtra("addr1", model.addr1)
            intent.putExtra("intro", model.intro)
            intent.putExtra("doNm", model.doNm)
            intent.putExtra("mapX", model.mapX)
            intent.putExtra("mapY", model.mapY)
            intent.putExtra("homepage", model.homepage)
            intent.putExtra("induty", model.induty)
            Log.d("lsy",model.intro)
            ContextCompat.startActivity(holder.binding.root.context, intent, null)
        }

    }
}