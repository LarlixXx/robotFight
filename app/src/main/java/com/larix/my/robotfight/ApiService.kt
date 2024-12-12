package com.larix.my.robotfight

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    @GET("api/area/read_control.php")
    fun getStatus(): Call<Status>

    @GET("api/interface/get_user_data.php")
    fun getName(
        @Query("user_name") user_name:String
    ): Call<Name>

    @POST("api/area/user_online.php")
    fun setOnline(): Call<String>

    @POST("api/users/points_set.php?")
    fun givePoint(
        @Query("user_name") user_name:String,
        @Query("num_points") num_points:String
    ): Call<givePoints>

    @POST("api/robots/update.php")
    @FormUrlEncoded
    fun getFacts4(
        @Field("api") api: String,
        @Field("status") status: String,
        @Field("id") id: String,
    ): Call<String>

}

