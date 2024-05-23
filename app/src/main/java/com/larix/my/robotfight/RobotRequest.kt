package com.larix.my.robotfight

import com.google.gson.annotations.SerializedName
import retrofit2.http.Query

data class RobotRequest(
    @SerializedName("api") val api:String,
    @SerializedName("status") val status:String,
    @SerializedName("id") val id:String
)