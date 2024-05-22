package com.larix.my.robotfight

import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("facts")
    fun getFacts(): Call<List<Fact>>
}