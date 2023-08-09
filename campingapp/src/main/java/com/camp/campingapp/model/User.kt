package com.camp.campingapp.model

data class User(
    var username: String,
    var email: String,
    var uid: String
){

    constructor(): this("","","")
}
