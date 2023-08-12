package com.camp.campingapp.retrofit

import com.camp.campingapp.model.FesList
import com.camp.campingapp.model.NaverReverseGeocodeResponse
import com.camp.campingapp.model.ShopList
import com.camp.campingapp.model.TourList
import com.example.k0327_dum_test.model.campDoNmList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkServiceDoNm {
    @GET("camp/campList/{doNm}")
    fun getList(
        @Path("doNm") doNm: String
    ): Call<List<campDoNmList>>

    @GET("tour/tourAllList")
    fun GetTourList(): Call<List<TourList>>

    @GET("shop/shopAllList")
    fun GetShopList(): Call<List<ShopList>>

    @GET("fes/fesAllList")
    fun GetFesList(): Call<List<FesList>>


//    https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?coords=128.12345,37.98776&output=json

    //    @GET("/naveropenapi.apigw.ntruss.com/")
    @GET("/map-reversegeocode/v2/gc")
    @Headers(
        "X-NCP-APIGW-API-KEY-ID: 426fftzyvu",
        "X-NCP-APIGW-API-KEY: EbRpqWC1Aehm3Nv2UmftiLQNqLgIIUGjyRNFaVPV"
    )
    fun reverseGeocode(
        @Query("coords") coords: String?,
        @Query("output") output: String = "json"

    ): Call<NaverReverseGeocodeResponse>

//    fun reverseGeocode(
//        @Header("X-NCP-APIGW-API-KEY-ID") clientId: String,
//        @Header("X-NCP-APIGW-API-KEY") clientSecret: String,
//        @Query("coords") coords: String,
//        @Query("output") output: String = "json"
//    ): Call<NaverReverseGeocodeResponse>
}
