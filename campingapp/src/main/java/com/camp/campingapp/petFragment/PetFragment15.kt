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

class PetFragment15 : Fragment() {
       override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
           val view = inflater.inflate(R.layout.fragment_pet15, container, false)
           var donm = "15"
           var sigunguNm: String

           val button1: Button = view.findViewById(R.id.button15_1)
           button1.setOnClickListener {
               Log.d("dum", "click")

               sigunguNm = "달서구"
               val intent = Intent(activity, PetListActivity::class.java)
               intent.putExtra("donm", donm)
               intent.putExtra("sigunguNm", sigunguNm)
               startActivity(intent)
           }

           val button2: Button = view.findViewById(R.id.button15_2)
           button2.setOnClickListener {
               Log.d("dum", "click")

               sigunguNm = "달성군"
               val intent = Intent(activity, PetListActivity::class.java)
               intent.putExtra("donm", donm)
               intent.putExtra("sigunguNm", sigunguNm)
               startActivity(intent)
           }

           val button3: Button = view.findViewById(R.id.button15_3)
           button3.setOnClickListener {
               Log.d("dum", "click")

               sigunguNm = "동구"
               val intent = Intent(activity, PetListActivity::class.java)
               intent.putExtra("donm", donm)
               intent.putExtra("sigunguNm", sigunguNm)
               startActivity(intent)
           }

           val button4: Button = view.findViewById(R.id.button15_4)
           button4.setOnClickListener {
               Log.d("dum", "click")

               sigunguNm = "북구"
               val intent = Intent(activity, PetListActivity::class.java)
               intent.putExtra("donm", donm)
               intent.putExtra("sigunguNm", sigunguNm)
               startActivity(intent)
           }

           val button5: Button = view.findViewById(R.id.button15_5)
           button5.setOnClickListener {
               Log.d("dum", "click")

               sigunguNm = "수성구"
               val intent = Intent(activity, PetListActivity::class.java)
               intent.putExtra("donm", donm)
               intent.putExtra("sigunguNm", sigunguNm)
               startActivity(intent)
           }

           return view
    }

}