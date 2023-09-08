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

class CaravanFragment4 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_caravan4, container, false)
        var donm = "4"
        var sigunguNm: String

        val button1: Button = view.findViewById(R.id.button4_1)
        button1.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "경산시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button2: Button = view.findViewById(R.id.button4_2)
        button2.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "경주시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button3: Button = view.findViewById(R.id.button4_3)
        button3.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "고령군"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button4: Button = view.findViewById(R.id.button4_4)
        button4.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "고창군"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button5: Button = view.findViewById(R.id.button4_5)
        button5.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "구미시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button6: Button = view.findViewById(R.id.button4_6)
        button6.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "군위군"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button7: Button = view.findViewById(R.id.button4_7)
        button7.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "김천시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button8: Button = view.findViewById(R.id.button4_8)
        button8.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "문경시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button9: Button = view.findViewById(R.id.button4_9)
        button9.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "봉화군"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button10: Button = view.findViewById(R.id.button4_10)
        button10.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "상주시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button11: Button = view.findViewById(R.id.button4_11)
        button11.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "성주군"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button12: Button = view.findViewById(R.id.button4_12)
        button12.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "안동시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button13: Button = view.findViewById(R.id.button4_13)
        button13.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "영덕군"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button14: Button = view.findViewById(R.id.button4_14)
        button14.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "영양군"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button15: Button = view.findViewById(R.id.button4_15)
        button15.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "영주시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button16: Button = view.findViewById(R.id.button4_16)
        button16.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "영천시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button17: Button = view.findViewById(R.id.button4_17)
        button17.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "예천군"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button18: Button = view.findViewById(R.id.button4_18)
        button18.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "울릉군"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button19: Button = view.findViewById(R.id.button4_19)
        button19.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "울진군"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button20: Button = view.findViewById(R.id.button4_20)
        button20.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "의성군"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button21: Button = view.findViewById(R.id.button4_21)
        button21.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "정읍시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button22: Button = view.findViewById(R.id.button4_22)
        button22.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "청도군"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button23: Button = view.findViewById(R.id.button4_23)
        button23.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "청송군"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button24: Button = view.findViewById(R.id.button4_24)
        button24.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "칠곡군"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button25: Button = view.findViewById(R.id.button4_25)
        button25.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "포항시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        return view
    }

}