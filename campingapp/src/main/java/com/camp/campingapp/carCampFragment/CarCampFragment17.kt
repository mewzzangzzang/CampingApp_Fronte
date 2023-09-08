package com.camp.campingapp.carCampFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.camp.campingapp.CampCarActivity
import com.camp.campingapp.R

class CarCampFragment17 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_car_camp17, container, false)
        var donm = "17"
        var sigunguNm: String

        val button1: Button = view.findViewById(R.id.button17_1)
        button1.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "동구"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button2: Button = view.findViewById(R.id.button17_2)
        button2.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "북구"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button3: Button = view.findViewById(R.id.button17_3)
        button3.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "울주군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button4: Button = view.findViewById(R.id.button17_4)
        button4.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "중구"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }


        return view
    }

}