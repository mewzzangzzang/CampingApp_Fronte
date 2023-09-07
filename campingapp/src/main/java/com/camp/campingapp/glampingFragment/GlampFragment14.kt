package com.camp.campingapp.glampingFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.camp.campingapp.CampGlampingActivity
import com.camp.campingapp.R


class GlampFragment14 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_glamp14, container, false)
        var donm = "14"
        var sigunguNm: String

        val button1: Button = view.findViewById(R.id.button14_1)
        button1.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "금남면"
            val intent = Intent(activity, CampGlampingActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button2: Button = view.findViewById(R.id.button14_2)
        button2.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "세종시"
            val intent = Intent(activity, CampGlampingActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button3: Button = view.findViewById(R.id.button14_3)
        button3.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "연서면"
            val intent = Intent(activity, CampGlampingActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button4: Button = view.findViewById(R.id.button14_4)
        button4.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "전동면"
            val intent = Intent(activity, CampGlampingActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        return view
    }

}