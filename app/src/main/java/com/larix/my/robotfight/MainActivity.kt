package com.larix.my.robotfight

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.larix.my.robotfight.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.stream.loadUrl("https://robot-fight.ru/video/")
        binding.stream.setInitialScale(150)

        val arg = intent.extras
        val id = arg?.get("yourId")
        val api = "26952954eb652c3e797cf74b8e7b29bc9f447212"
        val url = "https://robot-fight.ru/"
        val statusW = "Forw"
        val statusB = "Back"
        val statusR = "Right"
        val statusL = "Left"
        val statuss = "Stop"

        //1 - black 2 - green 3 - blue 4 - grey

        val retrofit = Retrofit.Builder()
            .baseUrl("https://cat-fact.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)


        CoroutineScope(Dispatchers.IO).launch {
            val repos = service.getFacts()
            repos.enqueue(object :Callback<List<Fact>>{
                override fun onResponse(
                    call: Call<List<Fact>>,
                    response: Response<List<Fact>>,
                ) {
                    println("!!!!!!!!${response.body()?.get(0)?.status}")
                }

                override fun onFailure(call: Call<List<Fact>>, t: Throwable) {
                    println("!!!!!!!!!error: ")
                }

            })
        }




        binding.btnW.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {

                    }

                    MotionEvent.ACTION_UP -> {

                    }

                }

                return v?.onTouchEvent(event) ?: true
            }
        })


    }
}