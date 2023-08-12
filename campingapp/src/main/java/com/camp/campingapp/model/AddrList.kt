package com.camp.campingapp.model

import com.google.gson.annotations.SerializedName

data class AddrList(
    var addr: List<addr>
)
data class addr(

    @SerializedName("name")
    var name:String
    


)
