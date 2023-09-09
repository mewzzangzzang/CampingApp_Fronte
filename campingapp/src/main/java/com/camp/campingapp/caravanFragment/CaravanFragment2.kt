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

class CaravanFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_caravan2, container, false)
        var donm = "2"
        var sigunguNm: String

        val button1: Button = view.findViewById(R.id.button2_1)
        button1.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "가평군"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button2: Button = view.findViewById(R.id.button2_2)
        button2.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "고양시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button3: Button = view.findViewById(R.id.button2_3)
        button3.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "과천시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button4: Button = view.findViewById(R.id.button2_4)
        button4.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "광명시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button5: Button = view.findViewById(R.id.button2_5)
        button5.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "광주시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button6: Button = view.findViewById(R.id.button2_6)
        button6.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "구리시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button7: Button = view.findViewById(R.id.button2_7)
        button7.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "군포시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button8: Button = view.findViewById(R.id.button2_8)
        button8.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "김포시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button9: Button = view.findViewById(R.id.button2_9)
        button9.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "남양주시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button10: Button = view.findViewById(R.id.button2_10)
        button10.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "동두천시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button11: Button = view.findViewById(R.id.button2_11)
        button11.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "성남시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button12: Button = view.findViewById(R.id.button2_12)
        button12.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "수원시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button13: Button = view.findViewById(R.id.button2_13)
        button13.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "시흥시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button14: Button = view.findViewById(R.id.button2_14)
        button14.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "안산시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button15: Button = view.findViewById(R.id.button2_15)
        button15.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "안성시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button16: Button = view.findViewById(R.id.button2_16)
        button16.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "안양시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button17: Button = view.findViewById(R.id.button2_17)
        button17.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "양주시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button18: Button = view.findViewById(R.id.button2_18)
        button18.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "양평군"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button19: Button = view.findViewById(R.id.button2_19)
        button19.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "여주시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button20: Button = view.findViewById(R.id.button2_20)
        button20.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "연천군"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button21: Button = view.findViewById(R.id.button2_21)
        button21.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "오산시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button22: Button = view.findViewById(R.id.button2_22)
        button22.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "용인시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button23: Button = view.findViewById(R.id.button2_23)
        button21.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "의왕시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button24: Button = view.findViewById(R.id.button2_24)
        button24.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "의정부시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button25: Button = view.findViewById(R.id.button2_25)
        button25.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "이천시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button26: Button = view.findViewById(R.id.button2_26)
        button26.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "파주시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button27: Button = view.findViewById(R.id.button2_27)
        button27.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "평택시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button28: Button = view.findViewById(R.id.button2_28)
        button28.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "포천시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button29: Button = view.findViewById(R.id.button2_29)
        button29.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "하남시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button30: Button = view.findViewById(R.id.button2_30)
        button30.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "화성시"
            val intent = Intent(activity, CaravanActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        return view
    }

}