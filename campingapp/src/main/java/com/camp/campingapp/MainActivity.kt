package com.camp.campingapp

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.camp.campingapp.databinding.ActivityMainBinding
import com.camp.campingapp.model.NaverReverseGeocodeResponse
import com.camp.campingapp.retrofit.NaverNetworkService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private var mFusedLocationProviderClient: FusedLocationProviderClient? =
        null //현재 위치를 가져오기 위한 변수
    lateinit var mLastLocation: Location // 위치 값을 가지고 있는 객체

    //    internal lateinit var mLocationRequest: LocationRequest // 위치 정보 요청의 매개변수를 저장하는
    lateinit var mLocationRequest: LocationRequest  // 위치 정보 요청의 매개변수를 저장하는
    private val REQUEST_PERMISSION_LOCATION = 10

    //    private var mapX : String = ""
//    private var mapY : String=""
    private var coords: String = ""

    private lateinit var auth : FirebaseAuth

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mLocationRequest = LocationRequest.create().apply {

            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 1000
            //초기화 시간

        }

        if (checkPermissionForLocation(this)) {
            startLocationUpdates()

        }

        val view = binding.root
        setContentView(view)

        val slidePanel = binding.mainFrame                      // SlidingUpPanel
//        slidePanel.addPanelSlideListener(PanelEventListener())  // 이벤트 리스너 추가

//        // 패널 열고 닫기
//        binding.btnToggle.setOnClickListener {
//            val state = slidePanel.panelState
//            // 닫힌 상태일 경우 열기
//            if (state == SlidingUpPanelLayout.PanelState.COLLAPSED) {
//                slidePanel.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
//            }
//            // 열린 상태일 경우 닫기
//            else if (state == SlidingUpPanelLayout.PanelState.EXPANDED) {
//                slidePanel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
//            }
//        }

        // 캠핑장
        binding.btnmenu1.setOnClickListener {
            val intent = Intent(this@MainActivity, CampDoNmActivity::class.java)
            startActivity(intent)
        }
        // 예약
        binding.btnmenu2.setOnClickListener {
            val intent = Intent(this@MainActivity, HostListActivity::class.java)
            startActivity(intent)
        }
        // 관광지
        binding.btnmenu3.setOnClickListener {
            val intent = Intent(this@MainActivity, Board::class.java)
            startActivity(intent)
        }
        // 커뮤니티
        binding.btnmenu4.setOnClickListener {
            val intent = Intent(this@MainActivity, TourActivity::class.java)
            startActivity(intent)
        }
//        로그인, 인증
        binding.btnlogin.setOnClickListener {
            val intent = Intent(this@MainActivity, AuthActivity::class.java)
            startActivity(intent)
        }
//        축제
        binding.btnmenu5.setOnClickListener {
            val intent = Intent(this@MainActivity, FesActivity::class.java)
            startActivity(intent)
        }
//        shop
        binding.btnmenu6.setOnClickListener {
            val intent = Intent(this@MainActivity,ShopActivity::class.java)
            startActivity(intent)
        }
//       chatmin
        binding.btnmenu7.setOnClickListener {
            val intent = Intent(this@MainActivity,ChatMainActivity::class.java)
            startActivity(intent)
        }

    }//oncreate


    inner class PanelEventListener : SlidingUpPanelLayout.PanelSlideListener {
        // 패널이 슬라이드 중일 때
        override fun onPanelSlide(panel: View?, slideOffset: Float) {
//            binding.tvSlideOffset.text = slideOffset.toString()
        }

        // 패널의 상태가 변했을 때
        override fun onPanelStateChanged(
            panel: View?,
            previousState: SlidingUpPanelLayout.PanelState?,
            newState: SlidingUpPanelLayout.PanelState?
        ) {
            if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
//                binding.btnToggle.text = "열기"
            } else if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
//                binding.btnToggle.text = "닫기"
            }
        }
    }
    private fun startLocationUpdates() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        mFusedLocationProviderClient!!.requestLocationUpdates(
            mLocationRequest,
            mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.lastLocation?.let { onLocationChanged(it) }
        }
    }

    private fun onLocationChanged(location: Location) {
        mLastLocation = location
        val coords = "${mLastLocation.longitude},${mLastLocation.latitude}"
        startApiCall(coords)
    }

    private fun startApiCall(coords: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://naveropenapi.apigw.ntruss.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val naverService = retrofit.create(NaverNetworkService::class.java)
        val call = naverService.reverseGeocode(coords, "json")

        call.enqueue(object : retrofit2.Callback<NaverReverseGeocodeResponse> {
            override fun onResponse(
                call: Call<NaverReverseGeocodeResponse>,
                response: Response<NaverReverseGeocodeResponse>
            ) {
                if (response.isSuccessful) {
                    val addressList = response.body()?.results
                    val firstAddress = addressList?.getOrNull(0)
                    val areaName = firstAddress?.region?.area1?.name
//                    Toast.makeText(this@MainActivity, "주소: $areaName", Toast.LENGTH_LONG).show()
                    binding.addressTextView.text = "$areaName"
                } else {
//                    Toast.makeText(this@MainActivity, "주소를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<NaverReverseGeocodeResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "API 호출이 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun checkPermissionForLocation(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                true
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_PERMISSION_LOCATION
                )
                false
            }
        } else {
            true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates()
            } else {
                Toast.makeText(this, "권한이 없어 해당 기능을 실행할 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
