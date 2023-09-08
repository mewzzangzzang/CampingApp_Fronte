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

class CommFragment12 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {

        val view = inflater.inflate(R.layout.fragment_comm12, container, false)
        var donm = "12"
        var sigunguNm: String

        val button1: Button = view.findViewById(R.id.button12_1)
        button1.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "괴산군"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button2: Button = view.findViewById(R.id.button12_2)
        button2.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "단양군"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button3: Button = view.findViewById(R.id.button12_3)
        button3.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "보은군"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button4: Button = view.findViewById(R.id.button12_4)
        button4.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "영동군"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button5: Button = view.findViewById(R.id.button12_5)
        button5.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "옥천군"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button6: Button = view.findViewById(R.id.button12_6)
        button6.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "음성군"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button7: Button = view.findViewById(R.id.button12_7)
        button7.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "제천시"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button8: Button = view.findViewById(R.id.button12_8)
        button8.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "증평군"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button9: Button = view.findViewById(R.id.button12_9)
        button9.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "진천군"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button10: Button = view.findViewById(R.id.button12_10)
        button10.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "청주시"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button11: Button = view.findViewById(R.id.button12_11)
        button11.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "충주시"
            val intent = Intent(activity, CampCommActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        return view
    }

}