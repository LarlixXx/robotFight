package com.larix.my.robotfight

import com.google.gson.annotations.SerializedName
import retrofit2.http.Query

data class DataRequest(
    @SerializedName("data") val data:RobotRequest

)