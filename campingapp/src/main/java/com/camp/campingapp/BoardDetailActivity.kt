package com.camp.campingapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.camp.campingapp.databinding.ActivityBoardDetailBinding
import com.camp.campingapp.model.BoardData
import retrofit2.Retrofit

class BoardDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityBoardDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("BoardTitle")
        val content = intent.getStringExtra("BoardContent")
        val date = intent.getStringExtra("BoardDate")

        binding.BoardTitle.text = title
        binding.BoardDate.text = date
        binding.BoardContent.text = content


    }

}