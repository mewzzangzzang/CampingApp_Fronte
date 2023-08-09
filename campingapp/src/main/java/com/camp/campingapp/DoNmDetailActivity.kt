package com.camp.campingapp

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.camp.campingapp.databinding.ActivityDoNmDetailBinding

class DoNmDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDoNmDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDoNmDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.facltNm.text = intent.getStringExtra("facltNm")
        binding.tel.text = intent.getStringExtra("tel")
        binding.lineIntro.text = intent.getStringExtra("lineIntro")
        val imgUrl: String? = intent.getStringExtra("urlImg")

        Glide.with(this)
            .asBitmap()
            .load(imgUrl)
            .into(object : CustomTarget<Bitmap>(200, 200) {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    binding.avatarView.setImageBitmap(resource)
//                    Log.d("lsy", "width : ${resource.width}, height: ${resource.height}")
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })
    }
}