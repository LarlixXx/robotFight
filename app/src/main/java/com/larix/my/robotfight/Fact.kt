package com.larix.my.robotfight

import com.google.gson.annotations.SerializedName

data class Fact(
    @SerializedName("status")
    val status: Status,
    @SerializedName("_id")
    val id:String,
    @SerializedName("user")
    val user: String,
    @SerializedName("text")
    val text:String,
    @SerializedName("__v")
    val v:Int,
    @SerializedName("source")
    val source:String,
    @SerializedName("updatedAt")
    val updatedAt:String,



)