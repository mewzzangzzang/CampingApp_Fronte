package com.camp.campingapp.model

import com.google.gson.annotations.SerializedName

data class TourListModel(

    var tours: List<TourList>
)
data class TourList(
    @SerializedName("tourid")
    var tourid : Long,
    @SerializedName("name")
    var name : String,
    @SerializedName("tourtype")
    var tourtype : String,
    @SerializedName("addr1")
    var addr1 : String,
    @SerializedName("addr2")
    var addr2 : String,
    @SerializedName("lat")
    var lat : Double,
    @SerializedName("lnt")
    var lnt : Double,
    @SerializedName("convenience")
    var convenience : String,
    @SerializedName("info")
    var info : String,
    @SerializedName("tel")
    var tel : String,
    @SerializedName("agencyname")
    var agencyname : String,
    @SerializedName("updatedate")
    var updatedate : String,
    @SerializedName("providername")
    var providername : String


)
