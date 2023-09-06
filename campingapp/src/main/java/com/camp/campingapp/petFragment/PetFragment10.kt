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


class PetFragment10 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pet10, container, false)
        var donm = "10"
        var sigunguNm: String

        val button1: Button = view.findViewById(R.id.button10_1)
        button1.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "서귀포시"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        val button2: Button = view.findViewById(R.id.button10_2)
        button2.setOnClickListener {
            Log.d("dum", "click")

            sigunguNm = "제주시"
            val intent = Intent(activity, PetListActivity::class.java)
            intent.putExtra("donm", donm)
            intent.putExtra("sigunguNm", sigunguNm)
            startActivity(intent)
        }

        return view
    }

}