package com.larix.my.robotfight

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    @POST("api/robots/update")
    @FormUrlEncoded
    fun getFacts1(@Query("data") data:RobotRequest): Call<List<DataRequest>>
@POST("api/robots/update")
    fun getFacts2(@Field("data") data:RobotRequest): Call<List<DataRequest>>
@POST("api/robots/update")
    @FormUrlEncoded
    fun getFacts3(@Body data:RobotRequest): Call<List<DataRequest>>
}

