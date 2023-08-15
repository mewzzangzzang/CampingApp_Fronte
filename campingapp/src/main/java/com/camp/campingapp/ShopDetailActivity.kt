package com.camp.campingapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.camp.campingapp.databinding.ActivityShopDetailBinding
import com.camp.campingapp.model.ShopList
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopDetailActivity : AppCompatActivity(), OnMapReadyCallback {
    private var mapView: com.naver.maps.map.MapView? = null
    lateinit var binding: ActivityShopDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.name.text = intent.getStringExtra("name")
        binding.addr.text = intent.getStringExtra("addr")
        binding.tel.text = intent.getStringExtra("tel")
        binding.info.text = intent.getStringExtra("info")
        var tel: String? = intent.getStringExtra("tel")

        // 전화 버튼
        binding.callBtn.setOnClickListener {
            var telNumber = "tel:${tel}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(telNumber))
            startActivity(intent)
        }

            //네이버 지도
            mapView = findViewById<View>(R.id.map_view) as com.naver.maps.map.MapView
            mapView!!.onCreate(savedInstanceState)
            mapView!!.getMapAsync(this@ShopDetailActivity)
        }

        override fun onMapReady(naverMap: NaverMap) {
            val networkService = (applicationContext as MyApplication).networkService
            val mapListCall = networkService.GetShopList()

            mapListCall.enqueue(object : Callback<List<ShopList>> {
                override fun onResponse(
                    call: Call<List<ShopList>>,
                    response: Response<List<ShopList>>

                ) {
                    var shopList = response.body()

                    // 마커 객체 생성
                    val marker = Marker()

                    val lat: Double = intent.getDoubleExtra("lat", Double.MAX_VALUE)
                    val lnt: Double = intent.getDoubleExtra("lnt", Double.MAX_VALUE)


                    // 가져온 위도, 경도 값으로 position 세팅
                    marker.setPosition(LatLng(lat, lnt))
                    marker.setMap(naverMap)

                    val cameraPosition = CameraPosition( // 카메라 위치 변경
                        LatLng(lat, lnt),  // 위치 지정
                        20.3 // 줌 레벨
                    )
                    naverMap.cameraPosition = cameraPosition


                }

                override fun onFailure(call: Call<List<ShopList>>, t: Throwable) {
                    Log.d("lsy", "fail")
                    call.cancel()
                }


            })


        }

        companion object {
            private val naverMap: NaverMap? = null
        }

    }