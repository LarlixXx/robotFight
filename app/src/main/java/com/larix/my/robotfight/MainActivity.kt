package com.larix.my.robotfight

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.larix.my.robotfight.LoginActivity.Companion
import com.larix.my.robotfight.LoginActivity.Companion.logName
import com.larix.my.robotfight.LoginActivity.Companion.service2
import com.larix.my.robotfight.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var service: ApiService

    private lateinit var api: String
    private lateinit var id: String
    private var carId by Delegates.notNull<String>()
    private var num_points by Delegates.notNull<Int>()

    var status by Delegates.notNull<String>()
    var numTouch by Delegates.notNull<String>()
    var userOnline by Delegates.notNull<String>()



    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.stream.loadUrl("https://robot-fight.ru/video/")
        binding.stream.setInitialScale(150)

        setOnline()
        checkStatus()

        val arg = intent.extras
        id = arg?.get("carId").toString()
        binding.statusText.text = "Your car is ${id}"
        println("!!!!! $id")
        when (id){
            "Black" -> carId = "1"
            "Green" -> carId = "2"
            "Blue" -> carId = "3"
            "Gray" -> carId = "4"
        }
        println("!!!!! $carId")

        num_points = 0
        api = "26952954eb652c3e797cf74b8e7b29bc9f447212"
        val url = "https://robot-fight.ru/"
        val stepForward = "Forw"
        val stepBack = "Back"
        val stepRight = "Right"
        val stepLeft = "Left"
        val stop = "Stop"
        val LoginIntent = Intent(this, LoginActivity::class.java)



        val timer = object : CountDownTimer(250, 50) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                if (status == "4"){
                    print("!!!!!!! numTouch $numTouch")
                    binding.btnL.isEnabled = false
                    binding.btnR.isEnabled = false
                    binding.btnS.isEnabled = false
                    binding.btnW.isEnabled = false
                    var unPoints = 6 - numTouch.toInt()
                    binding.numTouchText.text = "Number of bomb touches is $unPoints"
                    givePoints(logName,unPoints.toString())
                    startActivity(LoginIntent)
                }else{
                    setOnline()
                }


            }
        }


        fun checkEnd(){
            checkStatus()
            timer.start()
        }




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
                        checkEnd()
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
                        checkEnd()
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
                        checkEnd()
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
                        checkEnd()
                    }

                }

                return v?.onTouchEvent(event) ?: true
            }
        })

    }

    fun drive(step: String) {
        println("!!!! $carId")
        val repos = service.getFacts4(api = api, status = step, id = carId)
        repos.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>,
            ) {
                println("!!!!!!!!${response.body()?.get(0)}")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                println("!!!!!!!!!error: ${call} ")
            }

        })

    }

    fun givePoints(name: String,num_point:String) {
        val repos = service2.givePoint(user_name = name,num_points = num_point)
        repos.enqueue(object : Callback<givePoints> {


            override fun onResponse(call: Call<givePoints>, response: Response<givePoints>) {

                println("!!!!!!!!${response.body()}")
            }

            override fun onFailure(call: Call<givePoints>, t: Throwable) {
                println("!!!!!!!!!error: ${call} ")
            }

        })

    }

    fun setOnline() {
        val repos = service2.setOnline()
        repos.enqueue(object : Callback<String> {


            override fun onResponse(call: Call<String>, response: Response<String>) {


            }

            override fun onFailure(call: Call<String>, t: Throwable) {

            }

        })

    }

    open fun checkStatus() {
        val repos2 = service2.getStatus()
        repos2.enqueue(object : Callback<Status> {


            override fun onResponse(call: Call<Status>, response: Response<Status>) {


                status = response.body()?.status ?:"8"
                numTouch = response.body()?.num_touch ?:"8"
                userOnline = response.body()?.user_online ?:"8"

                println("!!!!!!!!!! numTouch: ${response.body()?.num_touch ?:"qwe"}" +
                        "   online: ${response.body()?.user_online ?:"8"}" +
                        "   status: ${response.body()?.status ?:"8"}")

            }

            override fun onFailure(call: Call<Status>, t: Throwable) {
                println("!!!!!!!!!error: ${call} ")
            }

        })

    }

}