package com.camp.campingapp.caravanFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.camp.campingapp.CaravanActivity
import com.camp.campingapp.R

class CaravanFragment16 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_caravan16, container, false)
        var donm = "16"
        var sigunguNm: String

        val button1: Button = view.findViewById(R.id.button16_1)
        button1.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "대덕구"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button2: Button = view.findViewById(R.id.button16_2)
        button2.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "동구"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button3: Button = view.findViewById(R.id.button16_3)
        button3.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "서구"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button4: Button = view.findViewById(R.id.button16_4)
        button4.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "유성구"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button5: Button = view.findViewById(R.id.button16_5)
        button5.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "중구"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        return view
    }

}