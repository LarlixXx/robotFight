package com.larix.my.robotfight

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("path/to/endpoint")
    fun postRequest(@Body body: Map<String, Any>): Call<ResponseBody>
}