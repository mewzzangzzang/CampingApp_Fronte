package com.camp.campingapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.camp.campingapp.model.AddrList
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NaverMapActivity : AppCompatActivity(),OnMapReadyCallback {

        private var mapView: MapView? = null
        private val myLatLng: LatLng = LatLng(37.3399, 126.733)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_naver_map)
            mapView = findViewById<View>(R.id.map_view) as MapView
            mapView!!.onCreate(savedInstanceState)
            mapView!!.getMapAsync(this)
        }

        override fun onMapReady(naverMap: NaverMap) {
            val networkService = (applicationContext as MyApplication).networkService
            val mapListCall = networkService.GetAddrGps()

            mapListCall.enqueue(object : Callback<AddrList> {
                override fun onResponse(
                    call: Call<AddrList>,
                    response: Response<AddrList>

                ) {
                    var addr = response.body()

                    // 마커 객체 생성
                    val marker = Marker()

                    // DB의 첫번째 행 식당의 위도, 경도 값 가져와 변수에 넣기
                    val lat: Double = intent.getDoubleExtra("lat", Double.MAX_VALUE)
                    val lnt: Double = intent.getDoubleExtra("lnt", Double.MAX_VALUE)


                    // 가져온 위도, 경도 값으로 position 세팅
                    marker.setPosition(LatLng(lat, lnt))
                    marker.setMap(naverMap)

                    val cameraPosition = CameraPosition( // 카메라 위치 변경
                        LatLng(lat,lnt),  // 위치 지정
                        20.3 // 줌 레벨
                    )
                    naverMap.cameraPosition = cameraPosition


                }

                override fun onFailure(call: Call<AddrList>, t: Throwable) {
                    Log.d("lsy", "fail")
                    call.cancel()
                }


            })






        }

    companion object {
        private val naverMap: NaverMap? = null
    }

}




