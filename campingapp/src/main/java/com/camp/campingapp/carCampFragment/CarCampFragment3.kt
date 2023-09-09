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


class CarCampFragment3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_car_camp3, container, false)
        var donm = "3"
        var sigunguNm: String

        val button1: Button = view.findViewById(R.id.button3_1)
        button1.setOnClickListener {
            sigunguNm = "거제시"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button2: Button = view.findViewById(R.id.button3_2)
        button2.setOnClickListener {
            sigunguNm = "거창군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button3: Button = view.findViewById(R.id.button3_3)
        button3.setOnClickListener {
            sigunguNm = "고성군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button4: Button = view.findViewById(R.id.button3_4)
        button4.setOnClickListener {
            sigunguNm = "고흥군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button5: Button = view.findViewById(R.id.button3_5)
        button5.setOnClickListener {
            sigunguNm = "김해시"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button6: Button = view.findViewById(R.id.button3_6)
        button6.setOnClickListener {
            sigunguNm = "남해군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button7: Button = view.findViewById(R.id.button3_7)
        button7.setOnClickListener {
            sigunguNm = "밀양시"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button8: Button = view.findViewById(R.id.button3_8)
        button8.setOnClickListener {
            sigunguNm = "사천시"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button9: Button = view.findViewById(R.id.button3_9)
        button9.setOnClickListener {
            sigunguNm = "산청군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button10: Button = view.findViewById(R.id.button3_10)
        button10.setOnClickListener {
            sigunguNm = "양산시"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button11: Button = view.findViewById(R.id.button3_11)
        button11.setOnClickListener {
            sigunguNm = "의령군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button12: Button = view.findViewById(R.id.button3_12)
        button12.setOnClickListener {
            sigunguNm = "진주시"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button13: Button = view.findViewById(R.id.button3_13)
        button13.setOnClickListener {
            sigunguNm = "창녕군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button14: Button = view.findViewById(R.id.button3_14)
        button14.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "창원시"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button15: Button = view.findViewById(R.id.button3_15)
        button15.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "통영시"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button16: Button = view.findViewById(R.id.button3_16)
        button16.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "하동군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button17: Button = view.findViewById(R.id.button3_17)
        button17.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "함안군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button18: Button = view.findViewById(R.id.button3_18)
        button18.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "함양군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button19: Button = view.findViewById(R.id.button3_19)
        button19.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "합천군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }


        return view
    }

}