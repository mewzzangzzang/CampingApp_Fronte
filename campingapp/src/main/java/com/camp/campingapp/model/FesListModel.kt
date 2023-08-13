package com.camp.campingapp.model

import com.google.gson.annotations.SerializedName

data class FesListModel(
    var fes: List<FesList>

)
data class FesList(
    @SerializedName("fesId")
    var fesId : String,
    @SerializedName("fesname")
    var fesname : String,
    @SerializedName("addr")
    var addr : String,
    @SerializedName("startdate")
    var startdate : String,
    @SerializedName("enddate")
    var enddate : String,
    @SerializedName("content")
    var content : String,
    @SerializedName("tel")
    var tel : String,
    @SerializedName("pageaddr")
    var pageaddr : String,
    @SerializedName("lat")
    var lat : String,
    @SerializedName("lnt")
    var lnt : String,
)
