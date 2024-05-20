package com.larix.my.robotfight

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import com.larix.my.robotfight.databinding.ActivityMainBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.stream.loadUrl("https://robot-fight.ru/video/")
        binding.stream.setInitialScale(150)

        val arg = intent.extras
        val id = arg?.get("yourId")
        val api = "26952954eb652c3e797cf74b8e7b29bc9f447212"
        val url = "https://robot-fight.ru/api/robots/update.php"
        val statusW = "Forw"
        val statusB = "Back"
        val statusR = "Right"
        val statusL = "Left"
        val statuss = "Stop"
        //1 - black 2 - green 3 - blue 4 - grey

        val retrofit = Retrofit.Builder()
            .baseUrl("https://postman-echo.com/get?api=api_26952954eb652c3e797cf74b8e7b29bc9f447212&status=Forw&id=1")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val body = mapOf(
            "api" to "api_26952954eb652c3e797cf74b8e7b29bc9f447212",
            "status" to "Forw",
            "id" to "1"
        )


        binding.btnW.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        apiService.postRequest(body).enqueue(object : Callback<ResponseBody> {
                            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                                // handle the response
                            }

                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                // handle the failure
                            }
                        })
                    }

                    MotionEvent.ACTION_UP -> {

                    }

                }

                return v?.onTouchEvent(event) ?: true
            }
        })


    }
}