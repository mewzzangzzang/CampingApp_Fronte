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

class CarCampFragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_car_camp1, container, false)
        var donm = "1"
        var sigunguNm: String

        val button1: Button = view.findViewById(R.id.button1_1)
        button1.setOnClickListener {
            

            sigunguNm = "강릉시"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button2: Button = view.findViewById(R.id.button1_2)
        button2.setOnClickListener {
            

            sigunguNm = "고성군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button3: Button = view.findViewById(R.id.button1_3)
        button3.setOnClickListener {
            

            sigunguNm = "동해시"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button4: Button = view.findViewById(R.id.button1_4)
        button4.setOnClickListener {
            

            sigunguNm = "삼척시"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button5: Button = view.findViewById(R.id.button1_5)
        button5.setOnClickListener {
            

            sigunguNm = "속초시"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button6: Button = view.findViewById(R.id.button1_6)
        button6.setOnClickListener {
            

            sigunguNm = "양구군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button7: Button = view.findViewById(R.id.button1_7)
        button7.setOnClickListener {
            

            sigunguNm = "양양군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button8: Button = view.findViewById(R.id.button1_8)
        button8.setOnClickListener {
            

            sigunguNm = "영월군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button9: Button = view.findViewById(R.id.button1_9)
        button9.setOnClickListener {
            

            sigunguNm = "원주시"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button10: Button = view.findViewById(R.id.button1_10)
        button10.setOnClickListener {
            

            sigunguNm = "인제군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button11: Button = view.findViewById(R.id.button1_11)
        button11.setOnClickListener {
            

            sigunguNm = "정선군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button12: Button = view.findViewById(R.id.button1_12)
        button12.setOnClickListener {
            

            sigunguNm = "철원군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button13: Button = view.findViewById(R.id.button1_13)
        button13.setOnClickListener {
            

            sigunguNm = "춘천시"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button14: Button = view.findViewById(R.id.button1_14)
        button14.setOnClickListener {
            

            sigunguNm = "태백시"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button15: Button = view.findViewById(R.id.button1_15)
        button15.setOnClickListener {
            

            sigunguNm = "평창군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button16: Button = view.findViewById(R.id.button1_16)
        button16.setOnClickListener {
            

            sigunguNm = "홍천군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button17: Button = view.findViewById(R.id.button1_17)
        button17.setOnClickListener {
            

            sigunguNm = "화천군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button18: Button = view.findViewById(R.id.button1_18)
        button18.setOnClickListener {
            

            sigunguNm = "횡성군"
            val intent = Intent(activity, CampCarActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        return view
    }

}