package com.camp.campingapp.model

import java.io.Serializable

data class Host(
    val hid: String,
    val name: String,
    val hostName: String,
    val address: String,
    val tel: String,
    val intro: String
) : Serializable