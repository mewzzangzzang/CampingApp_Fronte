package com.camp.campingapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.camp.campingapp.R.id.map_view
import com.camp.campingapp.model.FesList
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FesDetailActivity : AppCompatActivity(),OnMapReadyCallback {
    private var mapView: com.naver.maps.map.MapView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fes_detail)

        //네이버 지도
        mapView = findViewById<View>(map_view) as com.naver.maps.map.MapView
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this@FesDetailActivity)
    }

    override fun onMapReady(naverMap: NaverMap) {
        val networkService = (applicationContext as MyApplication).networkService
        val mapListCall = networkService.GetFesList()

        mapListCall.enqueue(object : Callback<List<FesList>> {
            override fun onResponse(
                call: Call<List<FesList>>,
                response: Response<List<FesList>>

            ) {
                var fesList = response.body()

                // 마커 객체 생성
                val marker = Marker()

                // DB의 첫번째 행 식당의 위도, 경도 값 가져와 변수에 넣기
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

            override fun onFailure(call: Call<List<FesList>>, t: Throwable) {
                Log.d("lsy", "fail")
                call.cancel()
            }


        })


    }

    companion object {
        private val naverMap: NaverMap? = null
    }
}
