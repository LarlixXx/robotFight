package com.larix.my.robotfight

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiService {

    @POST("api/robots/update.php")
    @FormUrlEncoded
    fun getFacts4(
        @Field("api") api:String,
        @Field("status") status:String,
        @Field("id") id: String,
    ): Call<String>

}

