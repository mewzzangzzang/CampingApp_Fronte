package com.camp.campingapp.model

data class Message(
    var message: String?,
    var sendId: String?
){
    constructor():this("","")
}
