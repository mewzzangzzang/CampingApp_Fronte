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


class PetFragment6 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pet6, container, false)
        var donm = "6"
        var sigunguNm: String

        val button1: Button = view.findViewById(R.id.button6_1)
        button1.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "강동구"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button2: Button = view.findViewById(R.id.button6_2)
        button2.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "강북구"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button3: Button = view.findViewById(R.id.button6_3)
        button3.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "관악구"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button4: Button = view.findViewById(R.id.button6_4)
        button4.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "구로구"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button5: Button = view.findViewById(R.id.button6_5)
        button5.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "노원구"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button6: Button = view.findViewById(R.id.button6_6)
        button6.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "도봉구"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button7: Button = view.findViewById(R.id.button6_7)
        button7.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "마포구"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button8: Button = view.findViewById(R.id.button6_8)
        button8.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "서초구"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button9: Button = view.findViewById(R.id.button6_9)
        button9.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "성동구"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button10: Button = view.findViewById(R.id.button6_10)
        button10.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "성북구"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button11: Button = view.findViewById(R.id.button6_11)
        button11.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "은평구"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button12: Button = view.findViewById(R.id.button6_12)
        button12.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "종로구"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button13: Button = view.findViewById(R.id.button6_13)
        button13.setOnClickListener {
            Log.d("dum", "click")
            sigunguNm = "중랑구"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        return view
    }

}