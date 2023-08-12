package com.camp.campingapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.camp.campingapp.databinding.ActivityDoNmDetailBinding
import com.example.k0327_dum_test.model.campDoNmList
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import retrofit2.Call
import retrofit2.Response

class DoNmDetailActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var binding: ActivityDoNmDetailBinding
    private var mapView: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDoNmDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.facltNm.text = intent.getStringExtra("facltNm")
        binding.tel.text = intent.getStringExtra("tel")
        binding.lineIntro.text = intent.getStringExtra("lineIntro")
//        binding.induty.text = intent.getStringExtra("induty")
        binding.intro.text = intent.getStringExtra("intro")
        binding.sbrsCl.text = intent.getStringExtra("sbrsCl")
        binding.addr1.text = intent.getStringExtra("addr1")
        binding.animalCmgCl.text = intent.getStringExtra("animalCmgCl")
        val imgUrl: String? = intent.getStringExtra("urlImg")

//        binding.goDonmList.setOnClickListener {
//            val intent = Intent(
//                this@DoNmDetailActivity,
//                CampDoNmActivity::class.java
//            )
//            startActivity(intent)
//        }

        var tel : String? = intent.getStringExtra("tel")

        // 전화 버튼
        binding.callBtn.setOnClickListener {
            var telNumber = "tel:${tel}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(telNumber))
            startActivity(intent)
        }

        Glide.with(this)
            .asBitmap()
            .load(imgUrl)
            .into(object : CustomTarget<Bitmap>(200, 200) {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    binding.avatarView.setImageBitmap(resource)
//                    Log.d("lsy", "width : ${resource.width}, height: ${resource.height}")
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })

        mapView = findViewById<View>(R.id.map_view) as MapView
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this@DoNmDetailActivity)
    }

    override fun onMapReady(naverMap: NaverMap) {

        val networkService = (applicationContext as MyApplication).networkService
        val donm = intent.getStringExtra("doNm")
        val mapListCall = donm?.let { networkService.getList(it) }


//        Log.d("lsy", "url:" + userListCall.request().url().toString())

        mapListCall?.enqueue(object : retrofit2.Callback<List<campDoNmList>> {
            override fun onResponse(
                call: retrofit2.Call<List<campDoNmList>>,
                response: Response<List<campDoNmList>>
            ) {
                var campDoNmList = response.body()

                // 마커 객체 생성
                val marker = Marker()

                // DB의 첫번째 행 식당의 위도, 경도 값 가져와 변수에 넣기
                val lat: Double = intent.getDoubleExtra("mapX", Double.MAX_VALUE)
                val lnt: Double = intent.getDoubleExtra("mapY", Double.MAX_VALUE)


                // 가져온 위도, 경도 값으로 position 세팅
                marker.setPosition(LatLng(lnt, lat))
                marker.setMap(naverMap)

                val cameraPosition = CameraPosition( // 카메라 위치 변경
                    LatLng(lnt, lat),  // 위치 지정
                    17.3 // 줌 레벨
                )
                naverMap.cameraPosition = cameraPosition


            }

            override fun onFailure(call: Call<List<campDoNmList>>, t: Throwable) {
                Log.d("lsy", "fail")
                call.cancel()
            }


        })

    }
}