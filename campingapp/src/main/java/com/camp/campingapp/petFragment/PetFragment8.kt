package com.camp.campingapp.petFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.camp.campingapp.PetListActivity
import com.camp.campingapp.R


class PetFragment8 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pet8, container, false)
        var donm = "8"
        var sigunguNm: String

        val button1: Button = view.findViewById(R.id.button8_1)
        button1.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "강진군"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button2: Button = view.findViewById(R.id.button8_2)
        button2.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "곡성군"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button3: Button = view.findViewById(R.id.button8_3)
        button3.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "광양시"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button4: Button = view.findViewById(R.id.button8_4)
        button4.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "구례군"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button5: Button = view.findViewById(R.id.button8_5)
        button5.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "남주시"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button6: Button = view.findViewById(R.id.button8_6)
        button6.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "담양군"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button7: Button = view.findViewById(R.id.button8_7)
        button7.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "목포시"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button8: Button = view.findViewById(R.id.button8_8)
        button8.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "무안군"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button9: Button = view.findViewById(R.id.button8_9)
        button9.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "보성군"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button10: Button = view.findViewById(R.id.button8_10)
        button10.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "순천시"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button11: Button = view.findViewById(R.id.button8_11)
        button11.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "신안군"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button12: Button = view.findViewById(R.id.button8_12)
        button12.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "여수시"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button13: Button = view.findViewById(R.id.button8_13)
        button13.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "영광군"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button14: Button = view.findViewById(R.id.button8_14)
        button14.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "영암군"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button15: Button = view.findViewById(R.id.button8_15)
        button15.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "완도군"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button16: Button = view.findViewById(R.id.button8_16)
        button16.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "장성군"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button17: Button = view.findViewById(R.id.button8_17)
        button17.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "장흥군"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button18: Button = view.findViewById(R.id.button8_18)
        button18.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "진도군"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button19: Button = view.findViewById(R.id.button8_19)
        button19.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "함평군"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button20: Button = view.findViewById(R.id.button8_20)
        button20.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "해남군"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button21: Button = view.findViewById(R.id.button8_21)
        button21.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "화순군"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        return view
    }

}