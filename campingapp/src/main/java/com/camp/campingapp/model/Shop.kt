package com.camp.campingapp.model

import com.google.gson.annotations.SerializedName

data class Shop(


    @SerializedName("shopId")
    var shopId : String,
    @SerializedName("name")
    var name : String,
    @SerializedName("tel")
    var tel : String,
    @SerializedName("addr")
    var addr : String,
    @SerializedName("lat")
    var lat : String,
    @SerializedName("lnt")
    var lnt : String,
    @SerializedName("info")
    var info : String




)
