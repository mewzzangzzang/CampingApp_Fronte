package com.camp.campingapp.model

class BoardData {
    var docId: String? = null
    var title: String? = null
    var content: String? = null
    var date: String? = null
    var comment: String? = null
    var imageUrl: String? = null
    var username1: String? = null // Add this line for username
    val username: String? = null // 게시글 작성 시점의 작성자 username
}
