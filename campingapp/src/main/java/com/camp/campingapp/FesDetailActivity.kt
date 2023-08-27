package com.camp.campingapp


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.camp.campingapp.R.id.map_view
import com.camp.campingapp.databinding.ActivityFesDetailBinding
import com.camp.campingapp.model.FesList
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.MarkerIcons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FesDetailActivity : AppCompatActivity(),OnMapReadyCallback{
    private var mapView: com.naver.maps.map.MapView? = null
    lateinit var binding:ActivityFesDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityFesDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
       setContentView(binding.root)

        binding.fesname.text = intent.getStringExtra("fesname")
        binding.addr.text = intent.getStringExtra("addr")
        binding.startdate.text = intent.getStringExtra("startdate")
        binding.enddate.text = intent.getStringExtra("enddate")
        binding.tel.text = intent.getStringExtra("tel")
        binding.pageaddr.text = intent.getStringExtra("pageaddr")
        binding.content.text = intent.getStringExtra("content")
        var tel : String? = intent.getStringExtra("tel")
        var pageaddr : String? = intent.getStringExtra("tel")

        // 전화 버튼
        binding.callBtn.setOnClickListener {
            var telNumber = "tel:${tel}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(telNumber))
            startActivity(intent)
        }
//        page넘버
        binding.pageaddr.setOnClickListener {
            val pageUrl = intent.getStringExtra("pageaddr")
            if (pageUrl?.isNotEmpty() == true) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl))
                startActivity(intent)
            } else {
                // 유효하지 않은 URL이거나 빈 경우에 대한 처리
                Toast.makeText(this, "유효하지 않은 URL입니다.", Toast.LENGTH_SHORT).show()
            }
        }

        //네이버 지도
        mapView = findViewById<View>(map_view) as com.naver.maps.map.MapView
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this@FesDetailActivity)
        // ActionBar에 뒤로가기 버튼 활성화
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }



    // ActionBar의 뒤로가기 버튼 클릭 시 호출되는 메서드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed() // 이전 화면으로 돌아가기
            return true
        }
        return super.onOptionsItemSelected(item)
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

////                마커 커스텀 아이콘 설정
//                val customMarkerIcon = MarkerIcons.customImage(
//                    resources.getDrawable(R.drawable.custom_marker_icon) // 커스텀 마커 아이콘 리소스
//                )
//                marker.icon = customMarkerIcon
//
//
//                // 가져온 위도, 경도 값으로 position 세팅하기
//                marker.setPosition(LatLng(lat, lnt))
//                marker.setMap(naverMap)
//
//                // 마커 클릭 시 윈도우(말풍선) 커스텀 설정
//                marker.setOnClickListener { overlay ->
//                    val customInfoWindow = FesInfoWindowView(this@FesDetailActivity)
//                    customInfoWindow.setFesInfo(fesList) // 원하는 정보를 뷰에 설정하는 메소드 호출
//                    overlay.infoWindow = InfoWindow(customInfoWindow, marker.position)
//                    true
//                }

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


    }//onmapccc

    companion object {
        private val naverMap: NaverMap? = null
    }
}
