package com.larix.my.robotfight

import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("verified")
    val verified:Boolean,
    @SerializedName("sentCount")
    val sentCount:Int,
)