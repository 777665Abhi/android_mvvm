package com.abhishek.android_mvvm.network.respones

import com.google.gson.annotations.SerializedName

class ResponsePost {
    @SerializedName("id")
    var id:String?=null
    @SerializedName("userId")
    var userId:String?=null
    @SerializedName("tittle")
    var tittle:String?=null
    @SerializedName("body")
    var body:String?=null
}