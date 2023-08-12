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



}
