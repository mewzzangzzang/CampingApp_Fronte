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

class GlampFragment11 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):  View? {
        val view = inflater.inflate(R.layout.fragment_glamp11, container, false)
        var donm = "11"
        var sigunguNm: String

        val button1: Button = view.findViewById(R.id.button11_1)
        button1.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "공주시"
            val intent = Intent(activity, CampGlampingActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button2: Button = view.findViewById(R.id.button11_2)
        button2.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "금산군"
            val intent = Intent(activity, CampGlampingActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button3: Button = view.findViewById(R.id.button11_3)
        button3.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "논산시"
            val intent = Intent(activity, CampGlampingActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button4: Button = view.findViewById(R.id.button11_4)
        button4.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "당진시"
            val intent = Intent(activity, CampGlampingActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button5: Button = view.findViewById(R.id.button11_5)
        button5.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "보령시"
            val intent = Intent(activity, CampGlampingActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button6: Button = view.findViewById(R.id.button11_6)
        button6.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "부여군"
            val intent = Intent(activity, CampGlampingActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button7: Button = view.findViewById(R.id.button11_7)
        button7.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "서산시"
            val intent = Intent(activity, CampGlampingActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button8: Button = view.findViewById(R.id.button11_8)
        button8.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "서천군"
            val intent = Intent(activity, CampGlampingActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button9: Button = view.findViewById(R.id.button11_9)
        button9.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "아산시"
            val intent = Intent(activity, CampGlampingActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button10: Button = view.findViewById(R.id.button11_10)
        button10.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "예산군"
            val intent = Intent(activity, CampGlampingActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button11: Button = view.findViewById(R.id.button11_11)
        button11.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "천안시"
            val intent = Intent(activity, CampGlampingActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button12: Button = view.findViewById(R.id.button11_12)
        button12.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "청양군"
            val intent = Intent(activity, CampGlampingActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button13: Button = view.findViewById(R.id.button11_13)
        button13.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "태안군"
            val intent = Intent(activity, CampGlampingActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button14: Button = view.findViewById(R.id.button11_14)
        button14.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "홍성군"
            val intent = Intent(activity, CampGlampingActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        return view
    }

}