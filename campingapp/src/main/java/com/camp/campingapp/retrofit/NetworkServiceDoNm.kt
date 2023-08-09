package com.camp.campingapp.retrofit

import com.example.k0327_dum_test.model.campDoNmList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkServiceDoNm {
    @GET("camp/campList/{doNm}")
    fun getList(
        @Path("doNm") doNm : String
//        @Query("doNm") doNm : String
//        @Query("numOfRows") numOfRows: Int,
//        @Query("pageNo") pageNo: Int,
//        @Query("MobileOS") MobileOS : String,
//        @Query("MobileApp") MobileApp : String,
//        @Query("serviceKey") serviceKey: String?,
//        @Query("mapX") mapX: String?,
//        @Query("mapY") mapY: String?,
//        @Query("radius") radius : Int,
//        @Query("_type") _type : String
    ): Call<List<campDoNmList>>


}