package com.camp.campingapp.model

import java.util.ArrayList

class BoardData {
    var docId: String? = null
    var title: String? = null
    var content: String? = null
    var date: String? = null
    var cId: String? = null
    var comment: MutableList<String>? = ArrayList<String>()
}