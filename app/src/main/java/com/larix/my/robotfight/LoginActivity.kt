package com.larix.my.robotfight

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    companion object{
        lateinit var user_name:String
        private lateinit var service: ApiService
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        user_name = "guest"
        val url = "https://robot-fight.ru/"

        val qwe = findViewById<TextView>(R.id.qweqweqwe)
        val logBtn = findViewById<Button>(R.id.loginBtn)
        val edText = findViewById<EditText>(R.id.loginEditText)
        val registerBtn = findViewById<Button>(R.id.registerBtn)

        logBtn.setOnClickListener{
            var mName = edText.text.toString()
            findName(mName)

        }
        registerBtn.setOnClickListener{
            qwe.text = user_name
        }



        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
        val retrofit =
            Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient).build()

        service = retrofit.create(ApiService::class.java)


    }



    fun findName(name: String) {
        val repos = service.getName(user_name=name)
        repos.enqueue(object : Callback<Name> {


            override fun onResponse(call: Call<Name>, response: Response<Name>) {
                findViewById<TextView>(R.id.qweqweqwe).text = response.body()?.user_name ?: "guest"
                println("!!!!!!!!${response.body()}")
            }

            override fun onFailure(call: Call<Name>, t: Throwable) {
                println("!!!!!!!!!error: ${call} ")
            }

        })

    }
}