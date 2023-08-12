package com.camp.campingapp

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.camp.campingapp.databinding.ActivityNaverMapBinding
import com.camp.campingapp.model.NaverReverseGeocodeResponse
import com.camp.campingapp.retrofit.NaverNetworkService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.security.auth.callback.Callback


class NaverMapActivity : AppCompatActivity() {

        lateinit var binding: ActivityNaverMapBinding
        private var mFusedLocationProviderClient: FusedLocationProviderClient? = null //현재 위치를 가져오기 위한 변수
        lateinit var mLastLocation: Location // 위치 값을 가지고 있는 객체
        //    internal lateinit var mLocationRequest: LocationRequest // 위치 정보 요청의 매개변수를 저장하는
        lateinit var mLocationRequest: LocationRequest  // 위치 정보 요청의 매개변수를 저장하는
        private val REQUEST_PERMISSION_LOCATION = 10
        private var coords: String = ""

        override fun onCreate(savedInstanceState: Bundle?) {

            //키는 다른 파일에 저장해서, 불러와서 사용하고,
            // 키를 가지고 있는 파일은 .gitIgnore 등록 후 , 원격지에 푸쉬 안함.


            super.onCreate(savedInstanceState)
            binding = ActivityNaverMapBinding.inflate(layoutInflater)
            setContentView(binding.root)

            mLocationRequest = LocationRequest.create().apply {

            priority = LocationRequest.PRIORITY_HIGH_ACCURACY

            }

            binding.button.setOnClickListener {
                if (checkPermissionForLocation(this)) {
                    startLocationUpdates()

                }
            }


        }//onCreate 문 닫아요


        private fun startLocationUpdates() {

            //FusedLocationProviderClient의 인스턴스를 생성.
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
            // 기기의 위치에 관한 정기 업데이트를 요청하는 메서드 실행
            // 지정한 루퍼 스레드(Looper.myLooper())에서 콜백(mLocationCallback)으로 위치 업데이트를 요청
            mFusedLocationProviderClient!!.requestLocationUpdates(
                mLocationRequest,
                mLocationCallback,
                Looper.myLooper()
            )
        }

        // 시스템으로 부터 위치 정보를 콜백으로 받음
        private val mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                // 시스템에서 받은 location 정보를 onLocationChanged()에 전달
                locationResult.lastLocation
                locationResult.lastLocation?.let { onLocationChanged(it) }
            }
        }

        // 시스템으로 부터 받은 위치정보를 화면에 갱신해주는 메소드
        fun onLocationChanged(location: Location) {
            mLastLocation = location
//        binding.text2.text = "위도 : " + mLastLocation.latitude // 갱신 된 위도
//        binding.text1.text = "경도 : " + mLastLocation.longitude // 갱신 된 경도

//        mapX = mLastLocation.longitude.toString()
//        mapY = mLastLocation.latitude.toString()
            coords = (mLastLocation.longitude.toString() + "," + mLastLocation.latitude.toString())

            //여기서 api 호출을 시작해야함
            startApiCall()

        }

        private fun startApiCall() {

            mLocationRequest = LocationRequest.create().apply {

                priority = LocationRequest.PRIORITY_HIGH_ACCURACY


            }


//            val userListCall = networkService.GetAddrGps(coords, output)
//            userListCall.enqueue(object : retrofit2.Callback<AddrList> {
//                override fun onResponse(call: Call<AddrList>, response: Response<AddrList>) {
////                val userList = response.body()
//                    Log.d("lsy", "실행 여부 확인. userListCall.enqueue")
//
//                }
//
//                override fun onFailure(call: retrofit2.Call<AddrList>, t: Throwable) {
//                    Log.d("lsy", "fail")
//                    call.cancel()
//                }
//            })


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
                        // areaName을 사용하여 필요한 처리를 수행
                        Toast.makeText(this@NaverMapActivity, "주소: $areaName", Toast.LENGTH_LONG).show()
//                        binding.addressTextView.text = "주소: $areaName"
                    } else {
                       Toast.makeText(this@NaverMapActivity, "주소를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
//                        binding.addressTextView.text = "안녕하세요"
                        // API 호출은 성공했지만 응답이 실패한 경우의 처리
                    }
                }


                override fun onFailure(call: Call<NaverReverseGeocodeResponse>, t: Throwable) {
                   Toast.makeText(this@NaverMapActivity, "API 호출이 실패했습니다.", Toast.LENGTH_SHORT).show()
//                    binding.addressTextView.text = "API 호출이 실패했습니다."
                    // API 호출 자체가 실패한 경우의 처리
                }

            })
        }

        // 위치 권한이 있는지 확인하는 메서드
        private fun checkPermissionForLocation(context: Context): Boolean {
            // Android 6.0 Marshmallow 이상에서는 위치 권한에 추가 런타임 권한이 필요
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (context.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    true
                } else {
                    // 권한이 없으므로 권한 요청 알림 보내기
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

        // 사용자에게 권한 요청 후 결과에 대한 처리 로직
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
