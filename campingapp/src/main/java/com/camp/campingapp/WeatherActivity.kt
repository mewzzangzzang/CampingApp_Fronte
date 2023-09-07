package com.camp.campingapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.camp.campingapp.databinding.ActivityWeatherBinding
import com.camp.campingapp.model.ModelWeather
import com.camp.campingapp.retrofit.ApiObject
import com.camp.campingapp.retrofit.ITEM
import com.camp.campingapp.retrofit.WEATHER
import com.camp.campingapp.recycler.WeatherAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class WeatherActivity : AppCompatActivity() {
//   lateinit var binding: ActivityWeatherBinding
//    private var mFusedLocationProviderClient: FusedLocationProviderClient? =
//        null //현재 위치를 가져오기 위한 변수
//    lateinit var mLastLocation: Location // 위치 값을 가지고 있는 객체
//
//    //    internal lateinit var mLocationRequest: LocationRequest // 위치 정보 요청의 매개변수를 저장하는
//    lateinit var mLocationRequest: LocationRequest  // 위치 정보 요청의 매개변수를 저장하는
//    private val REQUEST_PERMISSION_LOCATION = 10
//
//    private var mapX: String = ""
//    private var mapY: String = ""
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityWeatherBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        mLocationRequest = LocationRequest.create().apply {
//
//            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//            interval = 1000
//
//
//        }
//
//
//    } //oncreate 문닫아요
//
//    private fun startLocationUpdates() {
//
//        //FusedLocationProviderClient의 인스턴스를 생성.
//        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                android.Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//            && ActivityCompat.checkSelfPermission(
//                this,
//                android.Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            return
//        }
//        // 기기의 위치에 관한 정기 업데이트를 요청하는 메서드 실행
//        // 지정한 루퍼 스레드(Looper.myLooper())에서 콜백(mLocationCallback)으로 위치 업데이트를 요청
//        mFusedLocationProviderClient!!.requestLocationUpdates(
//            mLocationRequest,
//            mLocationCallback,
//            Looper.myLooper()
//        )
//    }
//
//    // 시스템으로 부터 위치 정보를 콜백으로 받음
//    private val mLocationCallback = object : LocationCallback() {
//        override fun onLocationResult(locationResult: LocationResult) {
//            // 시스템에서 받은 location 정보를 onLocationChanged()에 전달
//            locationResult.lastLocation
//            locationResult.lastLocation?.let { onLocationChanged(it) }
//        }
//    }
//
//    fun onLocationChanged(location: Location) {
//        mLastLocation = location
//
//        mapX = mLastLocation.longitude.toString()
//        mapY = mLastLocation.latitude.toString()
//
//        //여기서 api 호출을 시작해야함
//        startApiCall()
//
//    }
//
//    private fun startApiCall() {
//        mLocationRequest = LocationRequest.create().apply {
//            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//
//            val retrofit = Retrofit.Builder()
//                .baseUrl("https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//            val weatherService = retrofit.create(WeatherNetworkService::class.java)
//            val call = weatherService.getWeather(
//                ":XzoyE9IjEEyvVOl5pkvWteIzUfWJpTa9Q6QMcFwB6jpyKKXDjW8D9806MPeT%2F7DUt0GqSxVc4ZojctdTWtx2Yg%3D%3D",
//                "1",
//                "1",
//                "20230901",
//                "0600",
//                mapX,
//                mapY
//            )
//            call.enqueue(object : Callback<List<ShortWeatherResponse>> {
//                override fun onResponse(
//                    call: Call<List<ShortWeatherResponse>>,
//                    response: Response<List<ShortWeatherResponse>>
//                ) {
//                    if (response.isSuccessful) {
//                        val weatherList = response?.body()
//                        val skyList = weatherList?.getOrNull(0)
//                        val rainList = skyList?.body?.items?.item
////                       test
////                        Toast.makeText(this@WeatherActivity, "날씨:$rainList", Toast.LENGTH_SHORT)
////                            .show()
//                        binding.rainList.text = "$rainList"
//
//                    } else {
//                        Toast.makeText(this@WeatherActivity, "대박오류사건", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//
//                        // 날씨 정보에 따라 이미지를 표시
////                        when (rainList) {
////                            "비" -> {
////                                // 비 이미지를 로드
////                                Glide.with(this@WeatherActivity)
//////                                    .load(R.drawable.rain_image) // 비 이미지 리소스 ID 설정예정
//////                                    .into(weatherImageView)
////                            }
////                            "맑음" -> {
////                                // 맑음 이미지를 로드
////                                Glide.with(this@WeatherActivity)
//////                                    .load(R.drawable.sun_image) // 맑음 이미지 리소스 ID 설정예정
//////                                    .into(weatherImageView)
////                            }
////                            // 기타 날씨 상태에 대한 처리 추가
////                            else -> {
////                                // 기본 이미지를 로드
////                                Glide.with(this@WeatherActivity)
//////                                    .load(R.drawable.default_image) // 기본 이미지 리소스 ID 설정예정
//////                                    .into(weatherImageView)
////                            }
////                        }
////                    }
////                }
//
//                override fun onFailure(call: Call<List<ShortWeatherResponse>>, t: Throwable) {
//                    Toast.makeText(this@WeatherActivity,"호출실패",Toast.LENGTH_SHORT).show()
//                }
//
//            })
//
//        }
//
//
//    }
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == REQUEST_PERMISSION_LOCATION) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                startLocationUpdates()
//
//            } else {
//                Toast.makeText(this, "권한이 없어 해당 기능을 실행할 수 없습니다.", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//}
    val currentTime : Long = System.currentTimeMillis() // ms로 반환

    lateinit var weatherRecyclerView : RecyclerView

    private var base_date = "20230904"  // 발표 일자
    private var base_time = "1400"      // 발표 시각
    private var nx = "55"               // 예보지점 X 좌표
    private var ny = "127"              // 예보지점 Y 좌표

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)


        val tvDate = findViewById<TextView>(R.id.tvDate)                                // 오늘 날짜 텍스트뷰
        weatherRecyclerView = findViewById<RecyclerView>(R.id.weatherRecyclerView)  // 날씨 리사이클러 뷰
        val btnRefresh = findViewById<Button>(R.id.btnRefresh)                          // 새로고침 버튼

        // 리사이클러 뷰 매니저 설정
        weatherRecyclerView.layoutManager = LinearLayoutManager(this@WeatherActivity)

        // 오늘 날짜 텍스트뷰 설정
        tvDate.text = SimpleDateFormat("MM월 dd일", Locale.getDefault()).format(Calendar.getInstance().time) + "날씨"

        // nx, ny지점의 날씨 가져와서 설정하기
        setWeather(nx, ny)

        // <새로고침> 버튼 누를 때 날씨 정보 다시 가져오기
        btnRefresh.setOnClickListener {
            setWeather(nx, ny)

        }
    }

    // 날씨 가져와서 설정하기
    private fun setWeather(nx : String, ny : String) {
        // 준비 단계 : base_date(발표 일자), base_time(발표 시각)
        // 현재 날짜, 시간 정보 가져오기
        val cal = Calendar.getInstance()
        base_date = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(cal.time) // 현재 날짜
        val timeH = SimpleDateFormat("HH", Locale.getDefault()).format(cal.time) // 현재 시각
        val timeM = SimpleDateFormat("HH", Locale.getDefault()).format(cal.time) // 현재 분
        // API 가져오기 적당하게 변환
        base_time = getBaseTime(timeH, timeM)
        // 현재 시각이 00시이고 45분 이하여서 baseTime이 2330이면 어제 정보 받아오기
        if (timeH == "00" && base_time == "2330") {
            cal.add(Calendar.DATE, -1).toString()
            base_date = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(cal.time)
        }

        // 날씨 정보 가져오기
        // (한 페이지 결과 수 = 60, 페이지 번호 = 1, 응답 자료 형식-"JSON", 발표 날싸, 발표 시각, 예보지점 좌표)
        val call = ApiObject.retrofitService.GetWeather(60, 1, "JSON", base_date, base_time, nx, ny)

        // 비동기적으로 실행하기
        call.enqueue(object : retrofit2.Callback<WEATHER> {
            // 응답 성공 시
            override fun onResponse(call: Call<WEATHER>, response: Response<WEATHER>) {
                if (response.isSuccessful) {
                    // 날씨 정보 가져오기
                    val it: List<ITEM> = response.body()!!.response.body.items.item

                    // 현재 시각부터 1시간 뒤의 날씨 6개를 담을 배열
                    val weatherArr = arrayOf(ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather())

                    // 배열 채우기
                    var index = 0
                    val totalCount = response.body()!!.response.body.totalCount - 1
                    for (i in 0..totalCount) {
                        index %= 6
                        when(it[i].category) {
                            "PTY" -> weatherArr[index].rainType = it[i].fcstValue     // 강수 형태
                            "REH" -> weatherArr[index].humidity = it[i].fcstValue     // 습도
                            "SKY" -> weatherArr[index].sky = it[i].fcstValue          // 하늘 상태
                            "T1H" -> weatherArr[index].temp = it[i].fcstValue         // 기온
                            else -> continue
                        }
                        index++
                    }

                    // 각 날짜 배열 시간 설정
                    for (i in 0..5) weatherArr[i].fcstTime = it[i].fcstTime

                    // 리사이클러 뷰에 데이터 연결
                    weatherRecyclerView.adapter = WeatherAdapter(weatherArr)

                    // 토스트 띄우기
//                    Toast.makeText(applicationContext, it[0].fcstDate + ", " + it[0].fcstTime + "의 날씨 정보입니다.", Toast.LENGTH_SHORT).show()
                }
            }

            // 응답 실패 시
            override fun onFailure(call: Call<WEATHER>, t: Throwable) {
                Log.d("api fail", t.message.toString())
            }
        })
    }

    // baseTime 설정하기
    private fun getBaseTime(h : String, m : String) : String {
        var result = ""

        // 45분 전이면
        if (m.toInt() < 45) {
            // 0시면 2330
            if (h == "00") result = "2330"
            // 아니면 1시간 전 날씨 정보 부르기
            else {
                var resultH = h.toInt() - 1
                // 1자리면 0 붙여서 2자리로 만들기
                if (resultH < 10) result = "0" + resultH + "30"
                // 2자리면 그대로
                else result = resultH.toString() + "30"
            }
        }
        // 45분 이후면 바로 정보 받아오기
        else result = h + "30"

        return result
    }

}



