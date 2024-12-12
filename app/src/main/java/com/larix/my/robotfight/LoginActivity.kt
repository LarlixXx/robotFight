package com.larix.my.robotfight

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.properties.Delegates

class LoginActivity : AppCompatActivity() {
    companion object {
        lateinit var logName: String
        private lateinit var service: ApiService
        lateinit var service2: ApiService
        var carId by Delegates.notNull<String>()
    }
    private lateinit var onlik:String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        logName = "guest"
        carId = ""
        val url = "https://robot-fight.ru/"
        val url2 = "https://robot-fight.ru/poligon/"

        val logBtn = findViewById<Button>(R.id.loginBtn)
        val edText = findViewById<EditText>(R.id.loginEditText)
        val registerBtn = findViewById<Button>(R.id.registerBtn)
        val loginStat = findViewById<TextView>(R.id.loginStat)
        val MainMenuIntent = Intent(this, MainActivity::class.java)

        val timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                if (logName == "guest") {
                    loginStat.text = "login not exist"
                    logBtn.isEnabled = true
                    registerBtn.isEnabled = true
                } else {
                    if(onlik != "0"){
                        loginStat.text = "there is already a player, please wait"
                    }else{
                        MainMenuIntent.putExtra("carId", carId)
                        startActivity(MainMenuIntent)
                    }

                }


            }
        }

        logBtn.setOnClickListener {
            findName(edText.text.toString())
            checkStatus()
            logBtn.isEnabled = false
            registerBtn.isEnabled = false
            timer.start()
        }
        registerBtn.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://robot-fight.ru/wordpress/?page_id=24")
                )
            )
        }


        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

        val retrofit2 =
            Retrofit.Builder().baseUrl(url2).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient).build()

        service2 = retrofit2.create(ApiService::class.java)
        val retrofit =
            Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient).build()

        service = retrofit.create(ApiService::class.java)


    }


    open fun checkStatus() {
        val repos2 = service2.getStatus()
        repos2.enqueue(object : Callback<Status> {


            override fun onResponse(call: Call<Status>, response: Response<Status>) {
                carId = response.body()?.robot_name ?: "none"
                onlik = response.body()?.user_online ?: "8"


                println(
                    "!!!!!!!!!! robot_color: ${response.body()?.robot_name ?: "qwe"}" +
                            "   online: ${response.body()?.user_online ?: "8"}" +
                            "   status: ${response.body()?.status ?: "8"}"
                )

            }

            override fun onFailure(call: Call<Status>, t: Throwable) {
                println("!!!!!!!!!error: ${call} ")
            }

        })

    }

    fun findName(name: String) {
        val repos = service.getName(user_name = name)
        repos.enqueue(object : Callback<Name> {


            override fun onResponse(call: Call<Name>, response: Response<Name>) {
                logName = response.body()?.user_name ?: "guest"
                println("!!!!!!!!${response.body()}")
            }

            override fun onFailure(call: Call<Name>, t: Throwable) {
                println("!!!!!!!!!error: ${call} ")
            }

        })

    }
}