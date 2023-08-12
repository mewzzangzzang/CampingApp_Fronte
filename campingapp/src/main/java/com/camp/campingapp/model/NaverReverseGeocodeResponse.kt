package com.camp.campingapp.model

data class NaverReverseGeocodeResponse(
    val results: List<ReverseGeocodeResult>
)

data class ReverseGeocodeResult(
    val name: String,
    val region: ReverseGeocodeRegion
)

data class ReverseGeocodeRegion(
    val area1: ReverseGeocodeArea,
    // 이하 원하는 만큼의 지역 정보 추가
)

data class ReverseGeocodeArea(
    val name: String
    // 다른 정보들도 필요하면 추가
)
