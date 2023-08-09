package com.example.k0327_dum_test.model

import com.google.gson.annotations.SerializedName

data class ItemModel (
    @SerializedName("facltNm")
    var facltNm: String,
    @SerializedName("firstImageUrl")
    var firstImageUrl: String,
    @SerializedName("doNm")
    var doNm: String,
    @SerializedName("tel")
    var tel: String,
    @SerializedName("lineIntro")
    var lineIntro: String,
    @SerializedName("intro")
    var intro: String
)