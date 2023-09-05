package com.camp.campingapp.petFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.camp.campingapp.PetListActivity
import com.camp.campingapp.R
import android.content.Context
import android.util.Log

class PetFragment1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_pet1, container, false)
        var donm: String
        var sigunguNm: String

        val button1: Button = view.findViewById(R.id.button1_1)
        button1.setOnClickListener {
            Log.d("dum", "click")
            donm = "1"
            sigunguNm = "강릉시"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button2: Button = view.findViewById(R.id.button1_2)
        button2.setOnClickListener {
            Log.d("dum", "click")
            donm = "1"
            sigunguNm = "고성군"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button3: Button = view.findViewById(R.id.button1_3)
        button3.setOnClickListener {
            Log.d("dum", "click")
            donm = "1"
            sigunguNm = "동해시"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        return view
    }


}