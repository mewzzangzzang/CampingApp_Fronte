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

class CommFragment9 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_comm9, container, false)
        var donm = "9"
        var sigunguNm: String

        val button1: Button = view.findViewById(R.id.button9_1)
        button1.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "군산시"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button2: Button = view.findViewById(R.id.button9_2)
        button2.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "김제시"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button3: Button = view.findViewById(R.id.button9_3)
        button3.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "남원시"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button4: Button = view.findViewById(R.id.button9_4)
        button4.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "무주군"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button5: Button = view.findViewById(R.id.button9_5)
        button5.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "부안군"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button6: Button = view.findViewById(R.id.button9_6)
        button6.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "순창군"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button7: Button = view.findViewById(R.id.button9_7)
        button7.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "완주군"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button8: Button = view.findViewById(R.id.button9_8)
        button8.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "익산시"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button9: Button = view.findViewById(R.id.button9_9)
        button9.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "임실군"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button10: Button = view.findViewById(R.id.button9_10)
        button10.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "장수군"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button11: Button = view.findViewById(R.id.button9_11)
        button11.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "전주시"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button12: Button = view.findViewById(R.id.button9_12)
        button12.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "진안군"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        return view
    }
}