package com.larix.my.robotfight

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.larix.my.robotfight.LoginActivity.Companion.user_name
import com.larix.my.robotfight.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var service: ApiService
    private lateinit var api: String
    private lateinit var id: String

    val userId = user_name


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.stream.loadUrl("https://robot-fight.ru/video/")
        binding.stream.setInitialScale(150)

        val arg = intent.extras
        id = arg?.get("yourId").toString()

        api = "26952954eb652c3e797cf74b8e7b29bc9f447212"
        val url = "https://robot-fight.ru/"
        val stepForward = "Forw"
        val stepBack = "Back"
        val stepRight = "Right"
        val stepLeft = "Left"
        val stop = "Stop"

        fun carColor(id: String) =
            when (id) {
                "1" -> "Black"
                "2" -> "Green"
                "3" -> "Blue"
                "4" -> "Grey"
                else -> {
                    "Unknown"
                }
            }

        binding.statusText.text = "Your car is ${carColor(id)}"


        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
        val retrofit =
            Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient).build()

        service = retrofit.create(ApiService::class.java)



        binding.btnW.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        drive(stepForward)
                    }

                    MotionEvent.ACTION_UP -> {
                        drive(stop)
                    }

                }

                return v?.onTouchEvent(event) ?: true
            }
        })
        binding.btnL.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        drive(stepLeft)
                    }

                    MotionEvent.ACTION_UP -> {
                        drive(stop)
                    }

                }

                return v?.onTouchEvent(event) ?: true
            }
        })
        binding.btnR.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        drive(stepRight)
                    }

                    MotionEvent.ACTION_UP -> {
                        drive(stop)
                    }

                }

                return v?.onTouchEvent(event) ?: true
            }
        })
        binding.btnS.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        drive(stepBack)
                    }

                    MotionEvent.ACTION_UP -> {
                        drive(stop)
                    }

                }

                return v?.onTouchEvent(event) ?: true
            }
        })

    }

    fun drive(step: String) {

        val repos = service.getFacts4(api = api, status = step, id = id)
        repos.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>,
            ) {
                println("!!!!!!!!${response.body()?.get(0)}")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                println("!!!!!!!!!error: ")
            }

        })

    }
}