package com.camp.campingapp.commFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.camp.campingapp.CampCommActivity
import com.camp.campingapp.R


class CommFragment13 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_comm13, container, false)
        var donm = "13"
        var sigunguNm: String

        val button1: Button = view.findViewById(R.id.button13_1)
        button1.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "광산구"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button2: Button = view.findViewById(R.id.button13_2)
        button2.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "남구"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button3: Button = view.findViewById(R.id.button13_3)
        button3.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "서구"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button4: Button = view.findViewById(R.id.button13_4)
        button4.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "북구"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        return view
    }

}